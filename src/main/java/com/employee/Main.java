package com.employee;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------WELCOME TO EMPLOYEE PAYROLL SYSTEM------------");

        ArrayList<Employee> employeePayrollList=new ArrayList<>();
		EmployeePayroll employeePayroll=new EmployeePayroll(employeePayrollList);
		Scanner scanner=new Scanner(System.in);
		employeePayroll.readPayrollDetails(scanner);
		employeePayroll.writePayrollDetails();
    }
}