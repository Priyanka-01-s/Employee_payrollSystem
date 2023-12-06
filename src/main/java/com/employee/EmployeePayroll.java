package com.employee;

import java.util.List;
import java.util.Scanner;

public class EmployeePayroll {
    public enum IOService{
        CONSOLE_IO,
        FILE_IO,
        DB_IO,
        REST_IO
    }

    public List<Employee> employeePayrollList;

    public EmployeePayroll(List<Employee> employeePayrollList){
        this.employeePayrollList = employeePayrollList;
    }

    public void readPayrollDetails(Scanner sc){
        System.out.println("Enter employee id"); 
        int id = sc.nextInt();
        System.out.println("Enter employee name :");
        String name = sc.next();
        System.out.println("Enter employee salary: ");
        double salary = sc.nextDouble();
        employeePayrollList.add(new Employee(id, name, salary));
    }

    public void writePayrollDetails() {
        System.out.println("\nWriting Employee Payroll to Console\n"+ employeePayrollList);
    }
}

