package com.employee;

import java.io.EOFException;
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
    public static final String FILE_PATH = "employee_payroll.txt";

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

    public int numberEntries(){
        //System.out.println("Numbe of entries in the file :"+ employeePayrollList.size());
        int count =0;
        try(ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            // while(objectStream.readObject() != null){
            //     count++;
            // }
            while (true) {
                try {
                    objectStream.readObject();
                    count++;
                } catch (EOFException e) {
                    // End of file reached
                    break;
                }
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return count;
    }
}
