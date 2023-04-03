package taskfusion.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class Hooks {
    @Before
    public void beforeEachScenario() {

        //Clean singletons between tests
        SingletonHelpers.resetSingletons();
    }
}
