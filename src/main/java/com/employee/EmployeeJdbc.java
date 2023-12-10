package com.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeJdbc {

    public Connection getConnectivityTest() throws SQLException {
        // jdbc:mysql://localhost
        // write the proper url and root and pass from the workbench details
        // then / practice = db to use
       String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "riya123";
        try(
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password)
        ){
        System.out.println("Database connection successful!");
        }catch(Exception e){
        System.out.println("Error : Database connection failed!");
        e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
    
}
