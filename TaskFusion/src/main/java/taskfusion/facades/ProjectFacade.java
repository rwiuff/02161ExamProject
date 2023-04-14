package taskfusion.facades;

import java.util.List;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.ProjectActivity;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class ProjectFacade {
    private TaskFusion taskFusion;
    public ProjectRepository projectRepo = ProjectRepository.getInstance();

    public ProjectFacade(TaskFusion taskFusion) {
        this.taskFusion = taskFusion;
    }

    public void createProject(String projectTitle)
            throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Kun medarbejdere kan oprette et projekt");
        } else {
            Project project = projectRepo.create(projectTitle, taskFusion.getDate());
            // assign the user to the project
            String initials = taskFusion.getLoggedInUser().initials;
            project.assignEmployee(initials, EmployeeRepository.getInstance().findByInitials(initials));
        }
    }

    public void assignCustomerToProject(String projectNumber, String customer) throws NotFoundException {
        projectRepo.findByProjectNumber(projectNumber).setCustomer(customer);
    }

    public void assignEmployeeToProject(String projectNumber, String initials)
            throws NotFoundException, OperationNotAllowedException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.assignEmployee(initials, EmployeeRepository.getInstance().findByInitials(initials));
    }

    public ProjectViewModel findProjectByProjectNumber(String projectNumber) throws NotFoundException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        return project.toViewModel();
    }

    /**
     * ###########################
     * PROJECT ACTIVITY facades
     * ###########################
     * 
     * @throws AlreadyExistsException
     * @throws InvalidPropertyException
     */

    public void createProjectActivity(String projectNumber, String title, Integer startWeek, Integer endWeek)
            throws NotFoundException, OperationNotAllowedException, AlreadyExistsException, InvalidPropertyException {
        if (startWeek > endWeek) {
            throw new InvalidPropertyException("Starttid skal være før eller ens med sluttid");
        }

        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        } 
        
        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.createProjectActivity(new ProjectActivity(title, startWeek, endWeek), getLoggedInUserModel());
        
    }

    public void setTimeBudget(String projectNumber, String projectActivityTitle, Integer timeBudget)
            throws NotFoundException, OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }

        if (projectRepo.findByProjectNumber(projectNumber).getProjectLeader() != null) {
            if (!getLoggedInUserModel().getInitials()
                    .equals(projectRepo.findByProjectNumber(projectNumber).getProjectLeader().getInitials())) {
                System.out.println(getLoggedInUserModel().getInitials()
                        .equals(projectRepo.findByProjectNumber(projectNumber).getProjectLeader().getInitials()));
                throw new OperationNotAllowedException("Kun projektlederen kan tildele tidsbudgetter");
            }
        }

        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.findProjectActivity(projectActivityTitle).setTimeBudget(timeBudget);

    }

    public void registerWorkTime(String projectNumber, String activityTitle, double worktTime)
            throws NotFoundException, OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }
        projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
                .registerWorkTime(getLoggedInUserModel().getInitials(), taskFusion.getDate(), worktTime);
    }

    public double getTotalWorkTimeForEmployee(String projectNumber, String activityTitle, double workTime)
            throws NotFoundException, OperationNotAllowedException {

        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }
        return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
                .getTotalWorkTimeForEmployee(getLoggedInUserModel().getInitials());
    }

    public Double getTotalWorktimeForActivity(String projectNumber, String activityTitle)
            throws NotFoundException, OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }
        return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle).getTotalWorkTime();
    }

    public List<WorktimeRegistrationViewModel> getUserWorktimeRegistrationsForProjectActivity(String activityTitle,
            String projectNumber) throws NotFoundException, OperationNotAllowedException {

        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }

        List<WorktimeRegistration> worktimeRegistrationList = projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
        .getWorkTimeRegistrationsForEmployee(getLoggedInUserModel().getInitials());

        return WorktimeRegistrationViewModel.listFromModels(worktimeRegistrationList);
    }

    public double getUserWorktimeForProjectActivity(String activityTitle, String projectNumber)
            throws NotFoundException, OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }

        return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
                .getTotalWorkTimeForEmployee(getLoggedInUserModel().getInitials());
    }

    public void editWorktimeRegistration(int id, double hours) throws OperationNotAllowedException, NotFoundException {

        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }

        WorktimeRegistration worktimeRegistration = projectRepo.findWorktimeRegistrationById(id);

        if (!worktimeRegistration.getInitials().equals(getLoggedInUserModel().getInitials())) {
            throw new OperationNotAllowedException("Du har ikke rettighed til at redigere denne registrering");
        }

        worktimeRegistration.setTime(hours);

    }

    /**
     * ###########################
     * Helper methods
     * ###########################
     */
    private Employee getLoggedInUserModel() {
        return EmployeeRepository.getInstance().findByInitials(taskFusion.getLoggedInUser().initials);
    }
}
