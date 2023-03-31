package taskfusion.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class Hooks {
    @Before
    public void beforeEachScenario() {
        //System.out.println("Before each scenario");
        //Clean singletons between tests
        EmployeeRepository.resetInstance();
        ProjectRepository.resetInstance();
    }

    @After
    public void afterEachScenario() {
        //System.out.println("After each scenario");
    }
}
