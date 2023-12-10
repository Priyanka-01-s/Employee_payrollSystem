package com.employee;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{
    private int emp_id;
    private String name;
    private double salary;
    private Date startDate;

    public Employee(int emp_id, String name, double salary,Date startDate){
        this.emp_id = emp_id;
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
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
    public Date getdate(){
        return startDate;
    }

    @Override
    public String toString(){
        return "Employee-id :"+emp_id+ " Name :"+name+ " Salary :"+salary+"Start_date :"+startDate+"\n";
    } 
    
}
