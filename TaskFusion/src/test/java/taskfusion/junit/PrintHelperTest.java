package taskfusion.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.PrintHelper;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class PrintHelperTest {
    

    @BeforeEach
	public void resetSingletons() {
		SingletonHelpers.resetSingletons();
	}

	@Test
    public void testPrintEmployees() throws InvalidPropertyException, ExhaustedOptionsException  {
        EmployeeRepository.getInstance().create("Michael", "Laudrup");
        PrintHelper.printEmployees(EmployeeRepository.getInstance().all());
    }

    @Test 
    public void testPrintProjecs() throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, ExhaustedOptionsException {
        EmployeeRepository.getInstance().create("Michael", "Laudrup");
        Employee creator = EmployeeRepository.getInstance().findByInitials("mila");

        ProjectRepository.getInstance().create("Demo 1", creator, new DateServer().getDate());
        PrintHelper.printProjects(ProjectRepository.getInstance().all());
    }



}
