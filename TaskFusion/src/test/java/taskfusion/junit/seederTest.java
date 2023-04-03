package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.persistency.Seeder;


public class seederTest {


	@BeforeEach
	public void resetSingletons() {
		SingletonHelpers.resetSingletons();
	}

	@Test
	public void testSeedEmployees(){

		EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
		assertEquals(0, employeeRepository.all().size());

		Seeder seeder = new Seeder();
		seeder.seedDemoData();

		assertEquals(4, employeeRepository.all().size());

	}
	@Test
	public void testSeedProjects() {
		ProjectRepository projectRepository = ProjectRepository.getInstance();
		assertEquals(0, projectRepository.all().size());

		Seeder seeder = new Seeder();
		seeder.seedDemoData();

		assertEquals(2, projectRepository.all().size());
	}

}
