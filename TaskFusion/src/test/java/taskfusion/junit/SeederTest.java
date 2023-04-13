package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.domain.Project;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.persistency.Seeder;


public class SeederTest {


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

		assertEquals(4, projectRepository.all().size());

		//Assert users assigned
		for (Map.Entry<String, Project> projectEntry : ProjectRepository.getInstance().all().entrySet()) {

            Project project = projectEntry.getValue();

			assertEquals(4, project.getAssignedEmployees().size());


		}
	}

}
