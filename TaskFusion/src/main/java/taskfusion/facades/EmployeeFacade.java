package taskfusion.facades;

import java.util.List;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.RegularActivity;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.viewModels.EmployeeViewModel;
import taskfusion.viewModels.RegularActivityViewModel;

public class EmployeeFacade implements EmployeeFacadeInterface, RegularActivityFacadeInterface {
    private TaskFusion taskFusion;
    public EmployeeRepository employeeRepo = EmployeeRepository.getInstance();

    public EmployeeFacade(TaskFusion taskFusion) {
        this.taskFusion = taskFusion;
    }

    public void registerEmployee(String firstName, String lastName)
            throws InvalidPropertyException, ExhaustedOptionsException {
        employeeRepo.create(firstName, lastName);
    }

    public EmployeeViewModel findEmployeeByInitials(String initials) throws NotFoundException {

        Employee employee = employeeRepo.findByInitials(initials);
        return employee.toViewModel();
    }

    /**
     * ###########################
     * REGULAR ACTIVITIES
     * ###########################
     * @throws NotFoundException
     */

    public void createRegularActivity(String title, String startWeek, String endWeek)
            throws OperationNotAllowedException, InvalidPropertyException, NotFoundException {

        requireLogin();
        getLoggedInUserModel().addRegularActivity(new RegularActivity(title, startWeek, endWeek));
    }

    public boolean hasRegularActivity(String title, String startWeek, String endWeek) throws NotFoundException {
        return getLoggedInUserModel().hasRegularActivity(title, startWeek, endWeek);
    }

    public List<RegularActivityViewModel> getRegularActivities() throws NotFoundException {
        return RegularActivityViewModel.listFromModels(EmployeeRepository.getInstance()
                .findByInitials(taskFusion.getLoggedInUser().initials).getRegularActivities());
    }

    public RegularActivityViewModel getRegularActivityById(int id)
            throws NotFoundException, OperationNotAllowedException {

        requireLogin();

        RegularActivity activity = employeeRepo.findRegularActivityById(id);

        // Skal ned i domæne
        if (!getLoggedInUserModel().hasRegularActivityWithId(id)) {
            throw new OperationNotAllowedException("Du har ikke rettighed til at se denne aktivitet");
        }

        return activity.toViewModel();
    }

    // public void deleteRegularActivity(int id) throws OperationNotAllowedException
    // {

    // requireLogin();

    // getLoggedInUserModel().deleteRegularActivity(id);

    // }

    /**
     * ###########################
     * Helper methods
     * ###########################
     * @throws NotFoundException
     */
    private Employee getLoggedInUserModel() throws NotFoundException {
        return employeeRepo.findByInitials(taskFusion.getLoggedInUser().initials);
    }

    private void requireLogin() throws OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }
    }

}