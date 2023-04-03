package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.helpers.SingletonHelpers;

public class EmployeeTest {
    

    @BeforeEach
	public void resetSingletons() {
		SingletonHelpers.resetSingletons();
	}

	@Test
	public void testGetFullName() throws InvalidPropertyException, ExhaustedOptionsException {
        Employee employee = new Employee("Michael", "Laudrup");
        
        assertEquals("Michael Laudrup", employee.getFullName());
    }

}
