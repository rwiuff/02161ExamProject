package taskfusion.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.viewModels.EmployeeViewModel;

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
  public void testInitialGeneration() throws InvalidPropertyException, ExhaustedOptionsException {
    assertTrue(EmployeeRepository.getInstance().all().size() == 0);
    Object[] allValidInitialsForMichaelLaudrupInOrder = new Object[] {
      "mila", "milu", "mild", "milr", "milp", "miau", "miad", "miar", "miap",
      "miud", "miur", "miuu", "miup", "midr", "midu", "midp",  "miru", "mirp"
    };

    // Tests that generation order fits description, and verifies that there may be at most 18 Michael Laudrups
    for (int i = 0; i < 18; i++) {
      assertFalse(EmployeeRepository.getInstance().all().containsKey(allValidInitialsForMichaelLaudrupInOrder[i]));
      EmployeeRepository.getInstance().create("Michael", "Laudrup");
      assertTrue(EmployeeRepository.getInstance().all().containsKey(allValidInitialsForMichaelLaudrupInOrder[i]));
    }

    try {
      EmployeeRepository.getInstance().create("Michael", "Laudrup");
    } catch (Exception e) {
      assertEquals("Kunne ikke generere unikke initialer", e.getMessage());
    }
  }

  @Test
  public void testEmployeeViewModel() throws InvalidPropertyException, ExhaustedOptionsException {
    Employee employee = new Employee("Michael", "Laudrup");
    EmployeeViewModel employeeViewModel = employee.toViewModel();

    assertEquals("Michael", employeeViewModel.firstName);
    assertEquals("Laudrup", employeeViewModel.lastName);
    assertEquals("mila", employeeViewModel.initials);
    assertEquals("Michael Laudrup", employeeViewModel.fullName);
  }
}
