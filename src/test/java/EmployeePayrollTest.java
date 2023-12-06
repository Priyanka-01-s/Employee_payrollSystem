import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.employee.Employee;
import com.employee.EmployeePayroll;

class EmployeePayrollTest {

	@Test
	 void testCountEntries() {
        // Arrange
        List<Employee> testEmployeeList = new ArrayList<>();
        testEmployeeList.add(new Employee(1, "Priyanka", 50000.0));
        testEmployeeList.add(new Employee(2, "Hiii", 60000.0));

        EmployeePayroll employeePayrollService = new EmployeePayroll();

        // Act
        employeePayrollService.writeToFile();

        File file = new File(employeePayrollService.FILE_PATH);
        assertEquals(testEmployeeList.size(),employeePayrollService.numberEntries(), 2, "Count of entries does not match.");
    }

}