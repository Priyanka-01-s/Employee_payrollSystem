package com.employee;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeJdbc {

    // uc1 : for connection of database
    public Connection getConnectivityTest() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "riya123";
        try (
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.out.println("Error : Database connection failed!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    // uc2 for retrival of data from database
    public List<Employee> getPayrollData() throws SQLException, PayrollServiceException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = getConnectivityTest()) {
            String query = "SELECT * FROM EMPLOYEE_PAYROLL_DB";
            try (Statement statement = connection.createStatement();
                    ResultSet resSet = statement.executeQuery(query)) {
                while (resSet.next()) {
                    int id = resSet.getInt("EMP_ID");
                    String name = resSet.getString("NAME");
                    double salary = resSet.getDouble("SALARY");
                    Date startDate = resSet.getDate("START_DATE");
                    String phone = resSet.getString("PHONE");
                    String gender = resSet.getString("GENDER");
                    String department = resSet.getString("DEPARTMENT");
                    String basic_pay = resSet.getString("BASIC_PAY");
                    String deductions = resSet.getString("DEDUCTIONS");
                    String tax_pay = resSet.getString("TAXABLE_PAY");
                    String income_tax = resSet.getString("INCOME_TAX");
                    String net_pay = resSet.getString("NET_PAY");

                    // create an EmployeePayroll object and add it to the list
                    Employee employee = new Employee(id, name, gender, salary, startDate, phone, department,
                            basic_pay, deductions, tax_pay, income_tax, net_pay);
                    employees.add(employee);

                }

            } catch (SQLException e) {
                throw new PayrollServiceException("Error retrieving employee payroll data: " + e.getMessage());
            }
            return employees;
        }

    }

    // uc3 &4 - update values in db
    public void updatePayrollSalary(String name, double salary) throws SQLException, PayrollServiceException {
        try (Connection connection = getConnectivityTest()) {
            String updatequery = String.format("UPDATE EMPLOYEE_PAYROLL_DB SET SALARY = %.2f WHERE NAME = ?", salary);
            try (PreparedStatement preparedStatement = connection.prepareStatement(updatequery)) {
                preparedStatement.setString(1, name);
                int rows = preparedStatement.executeUpdate();

                if (rows == 0) {
                    System.out.println("Employee not found in the database!!!!");
                }
            }
        }
    }

    public List<Employee> getPayrollDataByDateRange(Date startDate, Date endDate)
            throws SQLException, PayrollServiceException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = getConnectivityTest()) {
            String query = "SELECT * FROM EMPLOYEE_PAYROLL_DB WHERE START_DATE BETWEEN ? AND ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

                try (ResultSet resSet = preparedStatement.executeQuery()) {
                    while (resSet.next()) {
                        int id = resSet.getInt("EMP_ID");
                        String name = resSet.getString("NAME");
                        double salary = resSet.getDouble("SALARY");
                        Date joinDate = resSet.getDate("START_DATE");
                        String phone = resSet.getString("PHONE");
                        String gender = resSet.getString("GENDER");
                        String department = resSet.getString("DEPARTMENT");
                        String basic_pay = resSet.getString("BASIC_PAY");
                        String deductions = resSet.getString("DEDUCTIONS");
                        String tax_pay = resSet.getString("TAXABLE_PAY");
                        String income_tax = resSet.getString("INCOME_TAX");
                        String net_pay = resSet.getString("NET_PAY");

                        Employee employee = new Employee(id, name, gender, salary, joinDate, phone, department,
                                basic_pay, deductions, tax_pay, income_tax, net_pay);
                        employees.add(employee);
                    }
                }
            }
        } catch (SQLException e) {
            throw new PayrollServiceException("Error retrieving employee payroll data: " + e.getMessage());
        }

        return employees;
    }

}

class PayrollServiceException extends Exception {
    public PayrollServiceException(String message) {
        super(message);
    }
}