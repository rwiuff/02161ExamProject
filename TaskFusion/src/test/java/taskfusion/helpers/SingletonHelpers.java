package taskfusion.helpers;

import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class SingletonHelpers {
    
    public static void resetSingletons() {
        EmployeeRepository.resetInstance();
        ProjectRepository.resetInstance();
    }

}
