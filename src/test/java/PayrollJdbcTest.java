import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//import java.sql.Date;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.employee.Employee;
import com.employee.EmployeeJdbc;
import com.employee.PayrollServiceException;

public class PayrollJdbcTest {
    
    @Test

    public void testAddEmployee(){
        try {
            // Assuming you have an instance of EmployeeJdbc
            EmployeeJdbc jdbc = new EmployeeJdbc();

            // Creating a new Employee object
            Employee newEmployee = new Employee(0, "Amit", "M", 50000.00,  new Date(), "1234567890", "IT",
                    "40000.00", "5000.00", "35000.00", "7000.00", "28000.00");

            // Adding the new employee to the database
            jdbc.addEmployee(newEmployee);

            //retrieving the employee from the database
            List<Employee> employees = jdbc.getPayrollData();
            Employee retrievedEmployee = null;

            for (Employee employee : employees) {
                if (employee.getName().equals("Amit")) {
                    retrievedEmployee = employee;
                    break;
                }
            }

            //asserting that the retrieved employee is not null
            assertNotNull(retrievedEmployee);

            assertEquals(newEmployee.getName(), retrievedEmployee.getName());
            assertEquals(newEmployee.getGender(), retrievedEmployee.getGender());
            assertEquals(newEmployee.getSalary(), retrievedEmployee.getSalary());

        } catch (SQLException | PayrollServiceException e) {
            e.printStackTrace();
        }
    }
}
