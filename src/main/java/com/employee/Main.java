package com.employee;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, PayrollServiceException, java.text.ParseException {
        System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

        EmployeeJdbc jdbc = new EmployeeJdbc();
        jdbc.getConnectivityTest();
        List<Employee> employeePayrolls = jdbc.getPayrollData();

        System.out.println("-----------THE DATABASE----------------\n");
        for (Employee employeePayroll : employeePayrolls) {
            System.out.println(employeePayroll);
        }

        System.out.println("-----------THE UPDATED DATABASE----------------\n");
        try {
            jdbc.updatePayrollSalary("Terissa", 3000000.00);

            List<Employee> employees = jdbc.getPayrollData();

            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } catch (SQLException | PayrollServiceException e) {
            e.printStackTrace();
        }

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startDate = dateFormat.parse("2023-03-31");
            java.util.Date endDate = dateFormat.parse("2023-04-31");

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(dateFormat.format(startDate));
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(dateFormat.format(endDate));

            // Retrieve employees in the specified date range
            List<Employee> employeesInDateRange = jdbc.getPayrollDataByDateRange(sqlStartDate, sqlEndDate);
            System.out.println("---------------------EMPLOYEEE FROM " + sqlStartDate + " TO " + sqlEndDate
                    + "----------------------\n");
            // Process the list of employees
            for (Employee employee : employeesInDateRange) {
                System.out.println(employee);
            }
        } catch (PayrollServiceException | SQLException | java.text.ParseException e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------SALARY ANALYSIS ON BASIS OF GENDER--------------\n");
        jdbc.genderSalaryAnalysis();

    }

}