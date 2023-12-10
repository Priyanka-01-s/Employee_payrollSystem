package com.employee;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, PayrollServiceException {
        System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

       EmployeeJdbc jdbc = new EmployeeJdbc();
       jdbc.getConnectivityTest();
       List<Employee> employeePayrolls = jdbc.getPayrollData();

            // Process the retrieved employee payroll data
            for (Employee employeePayroll : employeePayrolls) {
                System.out.println(employeePayroll);
            }

    }
}