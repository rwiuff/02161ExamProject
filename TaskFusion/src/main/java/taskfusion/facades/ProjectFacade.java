package taskfusion.facades;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.Report;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.EmployeeViewModel;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.ReportViewModel;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class ProjectFacade {
    private TaskFusion taskFusion;
    public ProjectRepository projectRepo = ProjectRepository.getInstance();

    public ProjectFacade(TaskFusion taskFusion) {
        this.taskFusion = taskFusion;
    }

    public void createProject(String projectTitle)
            throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
        requireLogin();

        Project project = projectRepo.create(projectTitle, taskFusion.getDate());
        // assign the user to the project
        String initials = taskFusion.getLoggedInUser().initials;
        project.assignEmployee(initials, EmployeeRepository.getInstance().findByInitials(initials));

    }

    public void assignCustomerToProject(String projectNumber, String customer) throws NotFoundException {
        projectRepo.findByProjectNumber(projectNumber).setCustomer(customer);
    }

    public void assignEmployeeToProject(String projectNumber, String initials)
            throws NotFoundException, OperationNotAllowedException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.assignEmployee(initials, getLoggedInUserModel());
    }

    public void takeProjectLeaderRole(String projectNumber) throws AlreadyExistsException, NotFoundException {
        projectRepo.findByProjectNumber(projectNumber).setProjectLeader(getLoggedInUserModel());
    }

    public ProjectViewModel findProjectByProjectNumber(String projectNumber) throws NotFoundException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        return project.toViewModel();
    }

    public ReportViewModel generateProjectRaport(String projectNumber)
            throws NotFoundException, OperationNotAllowedException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        requireLogin();
        Report report = new Report(project, taskFusion.getDate(), getLoggedInUserModel());
        project.addLatestReport(report.getDateAsString(), report);
        return report.toViewModel();
    }

    public void saveReport(String projectNumber, String reportDate, String saveDirectory) throws NotFoundException, IOException, URISyntaxException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        Report report = project.getReports().get(reportDate);
        report.saveReport(saveDirectory);
    }

    /**
     * ###########################
     * PROJECT ACTIVITY facades
     * ###########################
     * 
     * @throws AlreadyExistsException
     * @throws InvalidPropertyException
     */

    public void createProjectActivity(String projectNumber, String title, String startWeek, String endWeek)
            throws NotFoundException, OperationNotAllowedException, AlreadyExistsException, InvalidPropertyException {
        requireLogin();
        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.createProjectActivity(title, startWeek, endWeek, getLoggedInUserModel());

    }

    public void setTimeBudget(String projectNumber, String projectActivityTitle, Integer timeBudget)
            throws NotFoundException, OperationNotAllowedException {
        requireLogin();

        // Skal ned i domæne
        if (projectRepo.findByProjectNumber(projectNumber).getProjectLeader() != null) {
            if (!getLoggedInUserModel().getInitials()
                    .equals(projectRepo.findByProjectNumber(projectNumber).getProjectLeader().getInitials())) {
                throw new OperationNotAllowedException("Kun projektlederen kan tildele tidsbudgetter");
            }
        }

        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.findProjectActivity(projectActivityTitle).setTimeBudget(timeBudget);

    }

    public void registerWorkTime(String projectNumber, String activityTitle, double workTime)
            throws NotFoundException, OperationNotAllowedException {
        requireLogin();

        projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
                .registerWorkTime(getLoggedInUserModel().getInitials(), taskFusion.getDate(), workTime);
    }

    public double getTotalWorkTimeForEmployee(String projectNumber, String activityTitle, double workTime)
            throws NotFoundException, OperationNotAllowedException {

        requireLogin();

        return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
                .getTotalWorkTimeForEmployee(getLoggedInUserModel().getInitials());
    }


    public List<WorktimeRegistrationViewModel> getUserWorktimeRegistrationsForProjectActivity(String activityTitle,
            String projectNumber) throws NotFoundException, OperationNotAllowedException {

        requireLogin();

        List<WorktimeRegistration> worktimeRegistrationList = projectRepo.findByProjectNumber(projectNumber)
                .findProjectActivity(activityTitle)
                .getWorkTimeRegistrationsForEmployee(getLoggedInUserModel().getInitials());

        return WorktimeRegistrationViewModel.listFromModels(worktimeRegistrationList);
    }

    public double getUserWorktimeForProjectActivity(String activityTitle, String projectNumber)
            throws NotFoundException, OperationNotAllowedException {
        requireLogin();

        return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
                .getTotalWorkTimeForEmployee(getLoggedInUserModel().getInitials());
    }

    public List<WorktimeRegistrationViewModel> getTotalWorktimeRegistrationsForProject(String projectNumber)
            throws OperationNotAllowedException, NotFoundException {
        requireLogin();

        String projectLeader = projectRepo.findByProjectNumber(projectNumber).getProjectLeader().getInitials();

        // Skal ned i domæne
        if (!taskFusion.getLoggedInUser().initials.equals(projectLeader)) {
            throw new OperationNotAllowedException(
                    "Kun projektlederen kan tilgå oversigten af arbejdstid for projektet");
        }
        List<WorktimeRegistration> worktimeRegistrations = projectRepo.findByProjectNumber(projectNumber)
                .getWorktimeRegistrations();

        return WorktimeRegistrationViewModel.listFromModels(worktimeRegistrations);
    }

    public void editWorktimeRegistration(int id, double hours) throws OperationNotAllowedException, NotFoundException {

        requireLogin();

        WorktimeRegistration worktimeRegistration = projectRepo.findWorktimeRegistrationById(id);

        // Skal ned i domæne
        if (!worktimeRegistration.getInitials().equals(getLoggedInUserModel().getInitials())) {
            throw new OperationNotAllowedException("Du har ikke rettighed til at redigere denne registrering");
        }

        worktimeRegistration.setTime(hours);

    }

    public Double getRemainingWorktimeForActivity(String projectNumber, String activityTitle) throws NotFoundException {
        return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle).getRemainingWorktime();
    }

    public List<ProjectViewModel> getUserProjects() {

        List<Project> projects = new ArrayList<Project>(getLoggedInUserModel().getProjects().values());

        return ProjectViewModel.listFromModels(projects);
    }

    public List<EmployeeViewModel> getProjectEmployees(String projectNumber) {
        return EmployeeViewModel.listFromModels(ProjectRepository.getInstance().getListOfEmployees(projectNumber));
    }

    /**
     * ###########################
     * Helper methods
     * ###########################
     */
    private Employee getLoggedInUserModel() {
        return EmployeeRepository.getInstance().findByInitials(taskFusion.getLoggedInUser().initials);
    }

    private void requireLogin() throws OperationNotAllowedException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Login krævet");
        }
    }
}
