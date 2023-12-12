package com.employee;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{
    private int emp_id;
    private String name;
    private String gender;
    private double salary;
    private Date startDate;
    private String phone;
    private String department;
    private String basic_pay;
    private String deductions;
    private String tax_pay;
    private String income_tax;
    private String net_pay;

    public Employee(int emp_id, String name, String gender, double salary, Date startDate, String phone,
                    String department, String basic_pay, String deductions, String tax_pay, String income_tax, String net_pay) {
        this.emp_id = emp_id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.startDate = startDate;
        this.phone = phone;
        this.department = department;
        this.basic_pay = basic_pay;
        this.deductions = deductions;
        this.tax_pay = tax_pay;
        this.income_tax = income_tax;
        this.net_pay = net_pay;
    }

    public int getEmpId() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getBasicPay() {
        return basic_pay;
    }

    // public String getDeductions() {
    //     return deductions;
    // }

    // public String getTaxPay() {
    //     return tax_pay;
    // }

    // public String getIncomeTax() {
    //     return income_tax;
    // }

    // public String getNetPay() {
    //     return net_pay;
    // }
    public double getDeductions() {
        return 0.20 * salary; // Assuming 20% deduction
    }

    public double getTaxPay() {
        return salary - getDeductions();
    }

    public double getIncomeTax() {
        return 0.10 * getTaxPay(); // Assuming 10% tax on taxable pay
    }

    public double getNetPay() {
        return salary - getIncomeTax();
    }

    // Setter methods (assuming you need setters for these fields)
    public void setEmpId(int emp_id){
        this.emp_id =emp_id;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBasicPay(String basic_pay) {
        this.basic_pay = basic_pay;
    }

    public void setDeductions(String deductions) {
        this.deductions = deductions;
    }

    public void setTaxPay(String tax_pay) {
        this.tax_pay = tax_pay;
    }

    public void setIncomeTax(String income_tax) {
        this.income_tax = income_tax;
    }

    public void setNetPay(String net_pay) {
        this.net_pay = net_pay;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStartDate(Date date) {
        this.startDate = startDate;
    }

    public String toString() {
        return String.format("Employee Details%n" +
                "----------------------------------------%n" +
                "Employee ID: %d%n" +
                "Name: %s%n" +
                "Gender: %s%n" +
                "Salary: %.2f%n" +
                "Start Date: %s%n" +
                "Phone: %s%n" +
                "Department: %s%n" +
                "Basic Pay: %s%n" +
                "Deductions: %s%n" +
                "Tax Pay: %s%n" +
                "Income Tax: %s%n" +
                "Net Pay: %s%n" +
                "----------------------------------------%n",
                emp_id, name, gender, salary, startDate, phone, department,
                basic_pay, deductions, tax_pay, income_tax, net_pay);

        
   

    }

    
    
}
