package taskfusion.cucumber;

import io.cucumber.java.Before;
import taskfusion.helpers.SingletonHelpers;

public class Hooks {
    @Before
    public void beforeEachScenario() {

        // Clean singletons between tests
        SingletonHelpers.resetSingletons();
    }
}
