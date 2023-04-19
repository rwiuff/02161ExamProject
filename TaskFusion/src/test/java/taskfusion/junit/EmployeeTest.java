package taskfusion.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;

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

  @Test
	public void testBreakInitialGeneration() throws InvalidPropertyException, ExhaustedOptionsException {
    Object[] allValidInitialsForMichaelLaudrup = {"mila", "miau", "miup", "mild", "mirp", "miur", "miad", "miru", "miuu", "milr", "milp", "milu", "miap", "miud", "midr", "midp", "miar", "midu"};
    for (int i = 0; i < 18; i++) {
        EmployeeRepository.getInstance().create("Michael", "Laudrup"); 
    }

    Object[] initials = EmployeeRepository.getInstance().all().keySet().stream().toArray();
    assertArrayEquals(allValidInitialsForMichaelLaudrup, initials);
    try {
      EmployeeRepository.getInstance().create("Michael", "Laudrup");
    } catch (Exception e) {
      assertEquals("Kunne ikke generere unikke initialer", e.getMessage());
    }
  }
}

