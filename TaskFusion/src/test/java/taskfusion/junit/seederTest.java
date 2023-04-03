package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Project;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.persistency.Seeder;

/**
 * A test class to check which tests are run with which framework.
 * If run as a JUnit 5 (Jupiter) test, then both tests are run.
 * If run as a JUnit 4 test, then only the JUnit 4 tests are run.
 *
 * Remove the tests in your own projects.
 */
public class seederTest {

	// @org.junit.Before // Junit 4
	// public void setUp() {
	// 	System.out.println("setUp");
	// }

	// @org.junit.Test // JUnit 4
	// public void junit4Test() {
	// 	System.out.println("JUnit 4");
	// 	org.junit.Assert.assertTrue(true); // JUnit 4
	// }

	// @org.junit.jupiter.api.Test // Junit 5
	// public void junit5Test() {
	// 	System.out.println("JUnit 5");
	// 	org.junit.jupiter.api.Assertions.assertTrue(true); // JUnit 5
	// }

	@org.junit.Test
	public void testSeedEmployees(){

		EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
		assertEquals(0, employeeRepository.employees.size());;

		Seeder seeder = new Seeder();
		seeder.seedDemoData();

		assertEquals(4, employeeRepository.employees.size());

	}
	@org.junit.Test
	public void testSeedProjects() {
		ProjectRepository projectRepository = ProjectRepository.getInstance();
		assertEquals(0, projectRepository.projects.size());;

		Seeder seeder = new Seeder();
		seeder.seedDemoData();

		assertEquals(2, projectRepository.projects.size());
	}

}
