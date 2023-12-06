package com.employee;

import java.io.Serializable;

public class Employee implements Serializable{
    private int emp_id;
    private String name;
    private double salary;

    public Employee(int emp_id, String name, double salary){
        this.emp_id = emp_id;
        this.name = name;
        this.salary = salary;
    }

    //getter
    public int getEmpId(){
        return emp_id;
    }
    public String getName(){
        return name;
    }
    public double getSalary(){
        return salary;
    }

    @Override
    public String toString(){
        return "Employee-id :"+emp_id+ " Name :"+name+ " Salary :"+salary;
    } 
    
}
