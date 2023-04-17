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

        if(employee != null) {
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
    public void createRegularActivity(String title, Integer startWeek, Integer endWeek)
            throws OperationNotAllowedException, InvalidPropertyException {
        if (title == "") {
            throw new InvalidPropertyException("En titel mangler");
        }

        if (startWeek == null) {
            throw new InvalidPropertyException("En start uge mangler");
        }

        if (endWeek == null) {
            throw new InvalidPropertyException("En slut uge mangler");
        }

        if (startWeek > endWeek) {
            throw new InvalidPropertyException("Start uge skal være før slut uge");
        }

        if (endWeek < startWeek) {
            throw new InvalidPropertyException("Slut uge skal være før eller lig med start uge");
        }

        if (taskFusion.isLoggedIn()) {
            getLoggedInUserModel().addRegularActivity(new RegularActivity(title, startWeek, endWeek));
        } else {
            throw new OperationNotAllowedException("Kun medarbejdere kan oprette en fast aktivitet");
        }
    }

    public boolean hasRegularActivity(String title, Integer startWeek, Integer endWeek) {
        return getLoggedInUserModel().hasRegularActivity(title, startWeek, endWeek);
    }

    public List<RegularActivityViewModel> getRegularActivities() {
        return RegularActivityViewModel.listFromModels(EmployeeRepository.getInstance().findByInitials(taskFusion.getLoggedInUser().initials).getRegularActivities());
    }

    public RegularActivityViewModel getRegularActivityById(int id) throws NotFoundException, OperationNotAllowedException {

        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }

        RegularActivity activity = employeeRepo.findRegularActivityById(id);
        
        if(!getLoggedInUserModel().hasRegularActivity(id)) {
            throw new OperationNotAllowedException("Du har ikke rettighed til at se denne aktivitet");
        }

        return activity.toViewModel();
    }

    // public void deleteRegularActivity(int id) throws OperationNotAllowedException {
        
    //     requireLogin();

    //     getLoggedInUserModel().deleteRegularActivity(id);

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
