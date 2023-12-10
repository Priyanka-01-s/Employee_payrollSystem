package com.employee;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, PayrollServiceException {
        System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

        EmployeeJdbc jdbc = new EmployeeJdbc();
        jdbc.getConnectivityTest();
        List<Employee> employeePayrolls = jdbc.getPayrollData();

        System.out.println("-----------THE DATABASE----------------");

        // Process the retrieved employee payroll data
        for (Employee employeePayroll : employeePayrolls) {
            System.out.println(employeePayroll);
        }

        System.out.println("-----------THE UPDATED DATABASE----------------");
        try {
            jdbc.updatePayrollSalary("Terissa", 3000000.00);

            List<Employee> employees = jdbc.getPayrollData();

            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } catch (SQLException | PayrollServiceException e) {
            e.printStackTrace();
        }

    }
}