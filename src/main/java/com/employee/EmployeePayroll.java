package com.employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayroll {

    public enum IOService {
        CONSOLE_IO,
        FILE_IO,
        DB_IO,
        REST_IO
    }

    public List<Employee> employeePayrollList;
    private static final String FILE_PATH = "employee_payroll.txt";

    public EmployeePayroll() {
        this.employeePayrollList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeePayrollList.add(employee);
    }

    public void writeToFile() {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            //objectStream.writeObject(employeePayrollList);
            for (Employee employee : employeePayrollList) {
                String formattedEmployee = employee.toString();
                System.out.println(formattedEmployee); // Print to console
                objectStream.writeObject(formattedEmployee); // Write to file
            }
            System.out.println("Employee payroll data written to file succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object ob = objectInput.readObject();
            if (ob instanceof List) {
                employeePayrollList = (List<Employee>) ob;
                System.out.println("Employee Payroll data read from file successfully.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayEmployeePayroll() {
        System.out.println("Employee Payroll Details:");
        for (Employee employee : employeePayrollList) {
            System.out.println(employee);
        }
    }
}
