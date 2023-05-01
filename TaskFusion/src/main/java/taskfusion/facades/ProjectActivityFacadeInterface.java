package taskfusion.facades;

import java.util.List;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public interface ProjectActivityFacadeInterface {
    
    public void createProjectActivity(String projectNumber, String title, String startWeek, String endWeek) throws NotFoundException, OperationNotAllowedException, AlreadyExistsException, InvalidPropertyException;
    public void setTimeBudget(String projectNumber, String projectActivityTitle, Integer timeBudget) throws NotFoundException, OperationNotAllowedException;
    public void registerWorkTime(String projectNumber, String activityTitle, double workTime) throws NotFoundException, OperationNotAllowedException;
    public double getTotalWorkTimeForEmployee(String projectNumber, String activityTitle, double workTime) throws NotFoundException, OperationNotAllowedException;
    public List<WorktimeRegistrationViewModel> getUserWorktimeRegistrationsForProjectActivity(String activityTitle, String projectNumber) throws NotFoundException, OperationNotAllowedException;
    public double getUserWorktimeForProjectActivity(String activityTitle, String projectNumber) throws NotFoundException, OperationNotAllowedException;
    public List<WorktimeRegistrationViewModel> getTotalWorktimeRegistrationsForProject(String projectNumber) throws OperationNotAllowedException, NotFoundException;
    public void editWorktimeRegistration(int id, double hours) throws OperationNotAllowedException, NotFoundException;
    public Double getRemainingWorktimeForActivity(String projectNumber, String activityTitle) throws NotFoundException;
}
