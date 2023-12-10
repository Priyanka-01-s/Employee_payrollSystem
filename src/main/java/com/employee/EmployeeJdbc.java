package com.employee;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

public class EmployeeJdbc {

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

    //uc2 for retrival of data from database
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

                    //create an EmployeePayroll object and add it to the list
                    Employee employeePayroll = new Employee(id, name, salary, startDate);
                    employees.add(employeePayroll);

                }

            } catch (SQLException e) {
                throw new PayrollServiceException("Error retrieving employee payroll data: " + e.getMessage());
            }
            return employees;
        }

    }

}

class PayrollServiceException extends Exception {
        public PayrollServiceException(String message) {
            super(message);
        }
    }