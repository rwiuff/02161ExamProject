package taskfusion.app;

import java.util.Calendar;
import java.util.List;

import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.ProjectActivity;
import taskfusion.domain.RegularActivity;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class TaskFusion {

  private DateServer dateServer = new DateServer();
  private Employee loggedInUser;

  public ProjectRepository projectRepo = ProjectRepository.getInstance();
  public EmployeeRepository employeeRepo = EmployeeRepository.getInstance();

  /**
   * ###########################
   * USER / SESSION facades
   * ###########################
   */
  public Employee getLoggedInUser() {
    return loggedInUser;
  }

  public void login(String initials) throws NotFoundException {
    loggedInUser = findEmployeeByInitials(initials);

    if (loggedInUser == null) {
      throw new NotFoundException("Ukendt medarbejder");
    }
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
   * EMPLOYEE facades
   * ###########################
   */
  public void registerEmployee(String firstName, String lastName)
      throws InvalidPropertyException, ExhaustedOptionsException {
    employeeRepo.create(firstName, lastName);
  }

  public Employee findEmployeeByInitials(String initials) {
    return employeeRepo.findByInitials(initials);
  }

  public void setDateServer(DateServer dateServer) {
    this.dateServer = dateServer;
  }

     // ALLE DISSE CHECKS, SKAL FOREGÅ I DOMAIN LAYER, SO I SELVE REGULARACTIVITY KLASSEN
     public Calendar getDate() {
      return this.dateServer.getDate();
    }

  /**
   * ###########################
   * PROJECT facades
   * ###########################
   * 
   * @throws NotFoundException
   * @throws AlreadyExistsException
   */

  public void createProject(String projectTitle)
      throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
    if (!isLoggedIn()) {
      throw new OperationNotAllowedException("Kun medarbejdere kan oprette et projekt");
    } else {
      projectRepo.create(projectTitle, loggedInUser, this.dateServer.getDate());
    }
  }

  public void assignCustomerToProject(String projectNumber, String customer) throws NotFoundException {
    projectRepo.findByProjectNumber(projectNumber).setCustomer(customer);
  }

  public void assignEmployeeToProject(String projectNumber, String initials)
      throws NotFoundException, OperationNotAllowedException {
    Project project = projectRepo.findByProjectNumber(projectNumber);
    project.assignEmployee(initials, loggedInUser);
  }

  public Project findProjectByProjectNumber(String projectNumber) throws NotFoundException {
    Project project = projectRepo.findByProjectNumber(projectNumber);
    return project;
  }

  /**
   * ###########################
   * REGULAR ACTIVITY facades
   * ###########################
   */

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

    if (isLoggedIn()) {
      loggedInUser.addRegularActivity(new RegularActivity(title, startWeek, endWeek));
    } else {
      throw new OperationNotAllowedException("Kun medarbejdere kan oprette en fast aktivitet");
    }
  }

  public boolean hasRegularActivity(String title, Integer startWeek, Integer endWeek) {
    return loggedInUser.hasRegularActivity(title, startWeek, endWeek);
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

    if (!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    } else {
      Project project = findProjectByProjectNumber(projectNumber);
      project.createProjectActivity(new ProjectActivity(title, startWeek, endWeek), loggedInUser);
    }
  }

  public void setTimeBudget(String projectNumber, String projectActivityTitle, Integer timeBudget)
      throws NotFoundException, OperationNotAllowedException {
    if (!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    } 
    
    if (projectRepo.findByProjectNumber(projectNumber).getProjectLeader() != null) {
      if (!loggedInUser.getInitials().equals(projectRepo.findByProjectNumber(projectNumber).getProjectLeader().getInitials())) {
        System.out.println(loggedInUser.getInitials().equals(projectRepo.findByProjectNumber(projectNumber).getProjectLeader().getInitials()));
        throw new OperationNotAllowedException("Kun projektlederen kan tildele tidsbudgetter");
      }
    }

    Project project = findProjectByProjectNumber(projectNumber);
    project.findProjectActivity(projectActivityTitle).setTimeBudget(timeBudget);
    
  }

  public void registerWorkTime(String projectNumber, String activityTitle, double worktTime) throws NotFoundException, OperationNotAllowedException {
    if(!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    }
    projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
        .registerWorkTime(getLoggedInUser().getInitials(), this.dateServer.getDate(), worktTime);
  }

  public double getTotalWorkTimeForEmployee(String projectNumber, String activityTitle, double workTime)
      throws NotFoundException, OperationNotAllowedException {

        if(!isLoggedIn()) {
          throw new OperationNotAllowedException("Login krævet");
        }
    return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle)
        .getTotalWorkTimeForEmployee(getLoggedInUser().getInitials());
  }

  public Double getTotalWorktimeForActivity(String projectNumber, String activityTitle) throws NotFoundException, OperationNotAllowedException {
    if(!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    }
    return projectRepo.findByProjectNumber(projectNumber).findProjectActivity(activityTitle).getTotalWorkTime();
  }

  public List<WorktimeRegistration> getUserWorktimeRegistrationsForProjectActivity(String activityTitle,
      String projectNumber) throws NotFoundException, OperationNotAllowedException {

        if(!isLoggedIn()) {
          throw new OperationNotAllowedException("Login krævet");
        }

    return findProjectByProjectNumber(projectNumber).findProjectActivity(activityTitle)
        .getWorkTimeRegistrationsForEmployee(loggedInUser.getInitials());
  }

  public double getUserWorktimeForProjectActivity(String activityTitle, String projectNumber) throws NotFoundException, OperationNotAllowedException {
    if(!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    }
    
    return findProjectByProjectNumber(projectNumber).findProjectActivity(activityTitle)
        .getTotalWorkTimeForEmployee(loggedInUser.getInitials());
  }

  public void editWorktimeRegistration(int id, double hours) throws OperationNotAllowedException, NotFoundException {

    if(!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    }

    WorktimeRegistration worktimeRegistration = projectRepo.findWorktimeRegistrationById(id);

    if(!worktimeRegistration.getInitials().equals(loggedInUser.getInitials())) {
      throw new OperationNotAllowedException("Du har ikke rettighed til at redigere denne registrering");
    }
    
    worktimeRegistration.setTime(hours);

  }

}