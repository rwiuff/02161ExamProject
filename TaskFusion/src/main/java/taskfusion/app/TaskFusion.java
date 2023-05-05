package taskfusion.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

import taskfusion.domain.Project;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.facades.EmployeeFacade;
import taskfusion.facades.EmployeeFacadeInterface;
import taskfusion.facades.ProjectActivityFacadeInterface;
import taskfusion.facades.ProjectFacade;
import taskfusion.facades.ProjectFacadeInterface;
import taskfusion.facades.RegularActivityFacadeInterface;
import taskfusion.facades.ReportFacadeInterface;
import taskfusion.viewModels.EmployeeViewModel;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.RegularActivityViewModel;
import taskfusion.viewModels.ReportViewModel;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class TaskFusion implements AuthentificationInterface, ProjectFacadeInterface, ProjectActivityFacadeInterface, EmployeeFacadeInterface, RegularActivityFacadeInterface, ReportFacadeInterface {

  private DateServer dateServer = new DateServer();
  private EmployeeViewModel loggedInUser;

  private EmployeeFacade employeeFacade;
  private ProjectFacade projectFacade;

  public TaskFusion() {
    this.employeeFacade = new EmployeeFacade(this);
    this.projectFacade = new ProjectFacade(this);
  }

  /**
   * ###########################
   * AUTHENTIFICATION IMPLEMENTATIONS
   * ###########################
   */
  public EmployeeViewModel getLoggedInUser() {
    return loggedInUser;
  }

  public void login(String initials) throws NotFoundException {
    loggedInUser = findEmployeeByInitials(initials);
  }

  public boolean isLoggedIn() {
    // return true;
    return loggedInUser != null;
  }

  public void logout() {
    loggedInUser = null;
  }

  /**
   * ###########################
   * Date handling
   * ###########################
   */

  public void setDateServer(DateServer dateServer) {
    this.dateServer = dateServer;
  }

  public Calendar getDate() {
    return this.dateServer.getDate();
  }

  /**
   * ###########################
   * REGULAR ACTIVITY IMPLEMENTATIONS
   * ###########################
   */

  @Override
  public void createRegularActivity(String title, String startWeek, String endWeek)
      throws OperationNotAllowedException, InvalidPropertyException, NotFoundException {
    employeeFacade.createRegularActivity(title, startWeek, endWeek);
  }

  @Override
  public boolean hasRegularActivity(String title, String startWeek, String endWeek) throws NotFoundException {
    return employeeFacade.hasRegularActivity(title, startWeek, endWeek);
  }

  @Override
  public List<RegularActivityViewModel> getRegularActivities() throws NotFoundException {
    return employeeFacade.getRegularActivities();
  }

  @Override
  public RegularActivityViewModel getRegularActivityById(int id)
      throws NotFoundException, OperationNotAllowedException {
        return employeeFacade.getRegularActivityById(id);
  }

  @Override
  public void registerEmployee(String firstName, String lastName)
      throws InvalidPropertyException, ExhaustedOptionsException {
    employeeFacade.registerEmployee(firstName, lastName);
  }

  @Override
  public EmployeeViewModel findEmployeeByInitials(String initials) throws NotFoundException {
    return employeeFacade.findEmployeeByInitials(initials);
  }

  /**
   * ###########################
   * PROJECT ACTIVITY IMPLEMENTATIONS
   * ###########################
   */

  @Override
  public void createProjectActivity(String projectNumber, String title, String startWeek, String endWeek)
      throws NotFoundException, OperationNotAllowedException, AlreadyExistsException, InvalidPropertyException {
       projectFacade.createProjectActivity(projectNumber, title, startWeek, endWeek);
  }

  @Override
  public void setTimeBudget(String projectNumber, String projectActivityTitle, Integer timeBudget)
      throws NotFoundException, OperationNotAllowedException {
        projectFacade.setTimeBudget(projectNumber, projectActivityTitle, timeBudget);
  }

  @Override
  public void registerWorkTime(String projectNumber, String activityTitle, double workTime)
      throws NotFoundException, OperationNotAllowedException {
        projectFacade.registerWorkTime(projectNumber, activityTitle, workTime);
  }

  @Override
  public double getTotalWorkTimeForEmployee(String projectNumber, String activityTitle, double workTime)
      throws NotFoundException, OperationNotAllowedException {
        return projectFacade.getTotalWorkTimeForEmployee(projectNumber, activityTitle, workTime);
  }

  @Override
  public List<WorktimeRegistrationViewModel> getUserWorktimeRegistrationsForProjectActivity(String activityTitle,
      String projectNumber) throws NotFoundException, OperationNotAllowedException {
        return projectFacade.getUserWorktimeRegistrationsForProjectActivity(activityTitle, projectNumber);
  }

  @Override
  public double getUserWorktimeForProjectActivity(String activityTitle, String projectNumber)
      throws NotFoundException, OperationNotAllowedException {
        return projectFacade.getUserWorktimeForProjectActivity(activityTitle, projectNumber);
  }

  @Override
  public List<WorktimeRegistrationViewModel> getTotalWorktimeRegistrationsForProject(String projectNumber)
      throws OperationNotAllowedException, NotFoundException {
        return projectFacade.getTotalWorktimeRegistrationsForProject(projectNumber);
  }

  @Override
  public void editWorktimeRegistration(int id, double hours) throws OperationNotAllowedException, NotFoundException {
    projectFacade.editWorktimeRegistration(id, hours);
  }

  @Override
  public Double getRemainingWorktimeForActivity(String projectNumber, String activityTitle) throws NotFoundException {
    return projectFacade.getRemainingWorktimeForActivity(projectNumber, activityTitle);
  }

  /**
   * ###########################
   * PROJECT IMPLEMENTATIONS
   * ###########################
   */

  @Override
  public Project createProject(String title)
      throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
        return projectFacade.createProject(title);
  }

  @Override
  public void assignCustomerToProject(String projectNumber, String customerName) throws NotFoundException {
    projectFacade.assignCustomerToProject(projectNumber, customerName);
  }

  @Override
  public void assignEmployeeToProject(String projectNumber, String initials)
      throws NotFoundException, OperationNotAllowedException {
    projectFacade.assignEmployeeToProject(projectNumber, initials);
  }

  @Override
  public void takeProjectLeaderRole(String projectNumber) throws AlreadyExistsException, NotFoundException {
    projectFacade.takeProjectLeaderRole(projectNumber);
  }

  @Override
  public ProjectViewModel findProjectByProjectNumber(String projectNumber) throws NotFoundException {
    return projectFacade.findProjectByProjectNumber(projectNumber);
  }

  @Override
  public List<ProjectViewModel> getUserProjects() throws NotFoundException {
    return projectFacade.getUserProjects();
  }

  @Override
  public List<EmployeeViewModel> getProjectEmployees(String projectNumber) {
    return projectFacade.getProjectEmployees(projectNumber);
  }


  /**
   * ###########################
   * REPORT IMPLEMENTATIONS
   * ###########################
   */

  @Override
  public ReportViewModel generateProjectRaport(String projectNumber)
      throws NotFoundException, OperationNotAllowedException {
    return projectFacade.generateProjectRaport(projectNumber);
  }

  @Override
  public void saveReport(String projectNumber, String reportDate, String saveDirectory)
      throws NotFoundException, IOException, URISyntaxException {
    projectFacade.saveReport(projectNumber, reportDate, saveDirectory);
  }



}