package com.employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

		Scanner scanner=new Scanner(System.in);

        EmployeePayroll employeePayroll = new EmployeePayroll();

        // Read employee details from the console
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();

        System.out.print("Enter Employee Name: ");
        String empName = scanner.next();

        System.out.print("Enter Employee Salary: ");
        double empSalary = scanner.nextDouble();

        // Create an Employee object
        Employee employee = new Employee(empId, empName, empSalary);

        // Add the employee to the payroll
        employeePayroll.addEmployee(employee);

        // Write employee details to file
        employeePayroll.writeToFile();

        // Read employee details from file
        employeePayroll.readFromFile();

        // Display employee details
        employeePayroll.displayEmployeePayroll();

        scanner.close();

    }
}