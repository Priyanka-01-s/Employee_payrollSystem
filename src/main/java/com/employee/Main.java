package com.employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

		Scanner scanner=new Scanner(System.in);
        EmployeePayroll employeePayroll = new EmployeePayroll();

        boolean addEmployees = true;

        do{
             System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();

        System.out.print("Enter Employee Name: ");
        String empName = scanner.next();

        System.out.print("Enter Employee Salary: ");
        double empSalary = scanner.nextDouble();

        Employee employee = new Employee(empId, empName, empSalary);
        employeePayroll.addEmployee(employee);

        System.out.println("\nDo you want to continue adding employees? (yes/no): ");
        String input = scanner.next().toLowerCase();
        addEmployees = input.equals("yes");

        }while(addEmployees);             

        employeePayroll.writeToFile();

        employeePayroll.readFromFile();

        employeePayroll.displayEmployeePayroll();

        int numberOfEntries = employeePayroll.numberEntries();
        System.out.println("Number of Entries in the File: " + numberOfEntries);

        scanner.close();

    }
}