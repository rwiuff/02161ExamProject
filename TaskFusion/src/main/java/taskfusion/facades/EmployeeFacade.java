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

        Employee employee = employeeRepo.findByInitials(initials);

        if (employee != null) {
            return employee.toViewModel();
        }

        return null;
    }

    /**
     * ###########################
     * REGULAR ACTIVITIES
     * ###########################
     */

    // ALLE DISSE CHECKS, SKAL FOREGÅ I DOMAIN LAYER, SO I SELVE REGULARACTIVITY
    // KLASSEN
    public void createRegularActivity(String title, String startWeek, String endWeek)
            throws OperationNotAllowedException, InvalidPropertyException {

        requireLogin();
        getLoggedInUserModel().addRegularActivity(new RegularActivity(title, startWeek, endWeek));
    }

    public boolean hasRegularActivity(String title, String startWeek, String endWeek) {
        return getLoggedInUserModel().hasRegularActivity(title, startWeek, endWeek);
    }

    public List<RegularActivityViewModel> getRegularActivities() {
        return RegularActivityViewModel.listFromModels(EmployeeRepository.getInstance()
                .findByInitials(taskFusion.getLoggedInUser().initials).getRegularActivities());
    }

    public RegularActivityViewModel getRegularActivityById(int id)
            throws NotFoundException, OperationNotAllowedException {

        requireLogin();

        RegularActivity activity = employeeRepo.findRegularActivityById(id);

        // Skal ned i domæne
        if (!getLoggedInUserModel().hasRegularActivityByID(id)) {
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
     */
    private Employee getLoggedInUserModel() {
        return employeeRepo.findByInitials(taskFusion.getLoggedInUser().initials);
    }

    private void requireLogin() throws OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }
    }

}