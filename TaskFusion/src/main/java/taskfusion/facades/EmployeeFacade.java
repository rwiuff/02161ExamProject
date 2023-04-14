package taskfusion.facades;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.viewModels.EmployeeViewModel;

public class EmployeeFacade {
    private TaskFusion taskFusion;
    public EmployeeRepository employeeRepo = EmployeeRepository.getInstance();

    public EmployeeFacade(TaskFusion taskFusion) {
        this.taskFusion = taskFusion;
    }

    public void registerEmployee(String firstName, String lastName)
            throws InvalidPropertyException, ExhaustedOptionsException {
        employeeRepo.create(firstName, lastName);
    }

    public EmployeeViewModel findEmployeeByInitials(String initials) {
        return employeeRepo.findByInitials(initials).toViewModel();
    }

}
