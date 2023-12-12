package com.employee;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

            EmployeeJdbc jdbc = new EmployeeJdbc();

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. View all employees");
                System.out.println("2. Update salary for an employee");
                System.out.println("3. View employees in a date range");
                System.out.println("4. Add a new employee");
                System.out.println("5. View salary analysis based on gender");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        viewAllEmployees(jdbc);
                        break;
                    case 2:
                        updateEmployeeSalary(jdbc, scanner);
                        break;
                    case 3:
                        viewEmployeesInDateRange(jdbc, scanner);
                        break;
                    case 4:
                        addNewEmployee(jdbc, scanner);
                        break;
                    case 5:
                        viewSalaryAnalysis(jdbc);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void viewAllEmployees(EmployeeJdbc jdbc) {
        try {
            List<Employee> employeePayrolls = jdbc.getPayrollData();
            System.out.println("-----------THE DATABASE----------------\n");
            for (Employee employeePayroll : employeePayrolls) {
                System.out.println(employeePayroll);
            }
        } catch (SQLException | PayrollServiceException e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployeeSalary(EmployeeJdbc jdbc, Scanner scanner) {
        try {
            System.out.print("Enter the name of the employee to update salary: ");
            String name = scanner.nextLine();

            System.out.print("Enter the new salary: ");
            double newSalary = scanner.nextDouble();

            jdbc.updatePayrollSalary(name, newSalary);

            System.out.println("Salary updated successfully!");
        } catch (SQLException | PayrollServiceException e) {
            e.printStackTrace();
        }
    }

    private static void viewEmployeesInDateRange(EmployeeJdbc jdbc, Scanner scanner) {
        try {
            System.out.print("Enter the start date (yyyy-MM-dd): ");
            String startDateString = scanner.nextLine();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);

            System.out.print("Enter the end date (yyyy-MM-dd): ");
            String endDateString = scanner.nextLine();
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);

            List<Employee> employeesInDateRange = jdbc.getPayrollDataByDateRange(
                    new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));

            System.out.println("---------------------EMPLOYEES FROM " + startDate + " TO " + endDate + "----------------------\n");

            for (Employee employee : employeesInDateRange) {
                System.out.println(employee);
            }
        } catch (SQLException | PayrollServiceException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void addNewEmployee(EmployeeJdbc jdbc, Scanner scanner) {
        try {
            System.out.print("Enter the name of the new employee: ");
            String name = scanner.nextLine();

            System.out.print("Enter the gender (M/F): ");
            String gender = scanner.nextLine();

            System.out.print("Enter the salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter the start date (yyyy-MM-dd): ");
            String startDateString = scanner.nextLine();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);

            System.out.print("Enter the phone number: ");
            String phone = scanner.nextLine();

            System.out.print("Enter the department: ");
            String department = scanner.nextLine();

            System.out.print("Enter the basic pay: ");
            String basicPay = scanner.nextLine();

            System.out.print("Enter the deductions: ");
            String deductions = scanner.nextLine();

            System.out.print("Enter the tax pay: ");
            String taxPay = scanner.nextLine();

            System.out.print("Enter the income tax: ");
            String incomeTax = scanner.nextLine();

            System.out.print("Enter the net pay: ");
            String netPay = scanner.nextLine();

            Employee newEmployee = new Employee(0, name, gender, salary, startDate, phone, department,
                    basicPay, deductions, taxPay, incomeTax, netPay);

            jdbc.addEmployee(newEmployee);

            System.out.println("Employee added successfully!");
        } catch (SQLException | PayrollServiceException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void viewSalaryAnalysis(EmployeeJdbc jdbc) {
        try {
            System.out.println("\n-----------SALARY ANALYSIS ON BASIS OF GENDER--------------\n");
            jdbc.genderSalaryAnalysis();
        } catch (SQLException | PayrollServiceException e) {
            e.printStackTrace();
        }
    }
}
