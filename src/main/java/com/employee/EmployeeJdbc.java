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
            String query = "SELECT * FROM COMBINED_EMPLOYEEPAYROLL";
            try (Statement statement = connection.createStatement();
                    ResultSet resSet = statement.executeQuery(query)) {
                while (resSet.next()) {
                    int id = resSet.getInt("employee_id");
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
            String updatequery = String.format("UPDATE COMBINED_EMPLOYEEPAYROLL SET SALARY = %.2f WHERE NAME = ?", salary);
            try (PreparedStatement preparedStatement = connection.prepareStatement(updatequery)) {
                preparedStatement.setString(1, name);
                int rows = preparedStatement.executeUpdate();

                if (rows == 0) {
                    System.out.println("Employee not found in the database!!!!");
                }
            }
        }
    }

    // uc5 : get values by a date range
    public List<Employee> getPayrollDataByDateRange(Date startDate, Date endDate)
            throws SQLException, PayrollServiceException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = getConnectivityTest()) {
            String query = "SELECT * FROM COMBINED_EMPLOYEEPAYROLL WHERE START_DATE BETWEEN ? AND ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

                try (ResultSet resSet = preparedStatement.executeQuery()) {
                    while (resSet.next()) {
                        int id = resSet.getInt("employee_id");
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

    // uc6 : get salary infights based on gender
    public void genderSalaryAnalysis() throws SQLException, PayrollServiceException {
        try (Connection connection = getConnectivityTest()) {
            String query = "SELECT GENDER, SUM(SALARY) AS TOTAL_SALARY, AVG(SALARY) AS AVG_SALARY, " +
                    "MIN(SALARY) AS MIN_SALARY, " +
                    "MAX(SALARY) AS MAX_SALARY, " +
                    "COUNT(*) AS EMPLOYEE_COUNT " +
                    "FROM COMBINED_EMPLOYEEPAYROLL " +
                    "GROUP BY GENDER";

            try (Statement statement = connection.createStatement();
                    ResultSet resSet = statement.executeQuery(query)) {
                while (resSet.next()) {
                    String gender = resSet.getString("GENDER");
                    double totalSalary = resSet.getDouble("TOTAL_SALARY");
                    double avgSalary = resSet.getDouble("AVG_SALARY");
                    double minSalary = resSet.getDouble("MIN_SALARY");
                    double maxSalary = resSet.getDouble("MAX_SALARY");
                    int employeeCount = resSet.getInt("EMPLOYEE_COUNT");

                    System.out.println("Gender: " + gender);
                    System.out.println("Total Salary: " + totalSalary);
                    System.out.println("Average Salary: " + avgSalary);
                    System.out.println("Minimum Salary: " + minSalary);
                    System.out.println("Maximum Salary: " + maxSalary);
                    System.out.println("Number of Employees: " + employeeCount);
                    System.out.println("----------------------------");
                }
            }
        }
    }

    // UC_7 : Ability to add a new Employee to the Payroll

    // public void addEmployee(Employee newEmployee) throws SQLException, PayrollServiceException {
    //     try (Connection connection = getConnectivityTest()) {
    //         // SQL Insert Query
    //         String insertQuery = "INSERT INTO COMBINED_EMPLOYEEPAYROLL (NAME, GENDER, SALARY, START_DATE, PHONE, DEPARTMENT, BASIC_PAY, DEDUCTIONS, TAXABLE_PAY, INCOME_TAX, NET_PAY) " +
    //                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
    //             // Setting Parameters in the Prepared Statement
    //             preparedStatement.setString(1, newEmployee.getName());
    //             preparedStatement.setString(2, newEmployee.getGender());
    //             preparedStatement.setDouble(3, newEmployee.getSalary());
    //             preparedStatement.setDate(4, new java.sql.Date(newEmployee.getStartDate().getTime()));
    //             preparedStatement.setString(5, newEmployee.getPhone());
    //             preparedStatement.setString(6, newEmployee.getDepartment());
    //             preparedStatement.setString(7, newEmployee.getBasicPay());
    //             preparedStatement.setDouble(8, newEmployee.getDeductions());
    //             preparedStatement.setDouble(9, newEmployee.getTaxPay());
    //             preparedStatement.setDouble(10, newEmployee.getIncomeTax());
    //             preparedStatement.setDouble(11, newEmployee.getNetPay());
    
    //             // Execute Update
    //             int rowsAffected = preparedStatement.executeUpdate();
    
    //             // Handle Results
    //             if (rowsAffected == 1) {
    //                 ResultSet generateKeys = preparedStatement.getGeneratedKeys();
    //                 if (generateKeys.next()) {
    //                     int empId = generateKeys.getInt(1);
    //                     newEmployee.setEmpId(empId);
    //                     System.out.println("Employee added successfully to the database!");
    //                 } else {
    //                     System.out.println("Failed to retrieve employee_id after adding employee!");
    //                 }
    //             } else {
    //                 System.out.println("Failed to add employee to the database!");
    //             }
    //         }
    //     } catch (SQLException e) {
    //         // Exception Handling
    //         throw new PayrollServiceException("Error adding employee to the database: " + e.getMessage());
    //     }
    // }


    //UC_8 : Ability to also add to payroll details when a new Employee is added to the Payroll

    public void addEmployee(Employee newEmployee) throws SQLException, PayrollServiceException{
        try (Connection connection = getConnectivityTest()) {
            connection.setAutoCommit(false);
    
            String insertQueryEmployee = "INSERT INTO COMBINED_EMPLOYEEPAYROLL (NAME, GENDER, SALARY, START_DATE, PHONE, DEPARTMENT, BASIC_PAY, DEDUCTIONS, TAXABLE_PAY, INCOME_TAX, NET_PAY) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
            String insertQueryPayrollDetails = "INSERT INTO employee_payroll_extended (emp_id, deductions, taxable_pay, tax, net_pay) "
                    + "VALUES (?, ?, ?, ?, ?)";
    
            try (PreparedStatement preparedStatementEmployee = connection.prepareStatement(insertQueryEmployee,
                    Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatementPayrollDetails = connection.prepareStatement(insertQueryPayrollDetails)) {
    
                preparedStatementEmployee.setString(1, newEmployee.getName());
                preparedStatementEmployee.setString(2, newEmployee.getGender());
                preparedStatementEmployee.setDouble(3, newEmployee.getSalary());
                preparedStatementEmployee.setDate(4, new java.sql.Date(newEmployee.getStartDate().getTime()));
                preparedStatementEmployee.setString(5, newEmployee.getPhone());
                preparedStatementEmployee.setString(6, newEmployee.getDepartment());
                preparedStatementEmployee.setString(7, newEmployee.getBasicPay());
                preparedStatementEmployee.setDouble(8, newEmployee.getDeductions());
                preparedStatementEmployee.setDouble(9, newEmployee.getTaxPay());
                preparedStatementEmployee.setDouble(10, newEmployee.getIncomeTax());
                preparedStatementEmployee.setDouble(11, newEmployee.getNetPay());
    
                int rowsAffectedEmployee = preparedStatementEmployee.executeUpdate();
    
                if (rowsAffectedEmployee == 1) {
                    ResultSet generateKeys = preparedStatementEmployee.getGeneratedKeys();
                    if (generateKeys.next()) {
                        int empId = generateKeys.getInt(1);
                        try{
                             preparedStatementPayrollDetails.setInt(1, empId);
                        preparedStatementPayrollDetails.setDouble(2, (newEmployee.getDeductions()));
                        preparedStatementPayrollDetails.setDouble(3,(newEmployee.getTaxPay()));
                        preparedStatementPayrollDetails.setDouble(4,(newEmployee.getIncomeTax()));
                        preparedStatementPayrollDetails.setDouble(5,(newEmployee.getNetPay()));
                        }catch(NumberFormatException e){
                            e.printStackTrace();
                        }
      
                        int rowsAffectedPayrollDetails = preparedStatementPayrollDetails.executeUpdate();
    
                        if (rowsAffectedPayrollDetails == 1) {
                            connection.commit();
                            newEmployee.setEmpId(empId);
                            System.out.println("Employee and Payroll Details added successfully to the database!");
                        } else {
                            connection.rollback();
                            System.out.println("Failed to add Payroll Details to the database!");
                        }
                    }
                } else {
                    connection.rollback();
                    System.out.println("Failed to add employee to the database!");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new PayrollServiceException("Error adding employee and payroll details: " + e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }
    

}
