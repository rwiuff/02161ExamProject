package taskfusion.app;

import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.ProjectActivity;
import taskfusion.domain.RegularActivity;
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

  /**
   * ###########################
   * PROJECT facades
   * ###########################
   * @throws NotFoundException
   * @throws AlreadyExistsException
   */

   public void createProject(String projectTitle) throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
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
   * @throws AlreadyExistsException
   * @throws InvalidPropertyException
   */

  public void createProjectActivity(String projectNumber, String title, Integer startWeek, Integer endWeek) throws NotFoundException, OperationNotAllowedException, AlreadyExistsException, InvalidPropertyException {
    if (startWeek > endWeek) {
      throw new InvalidPropertyException("Starttid skal være før eller ens med sluttid");
    }

    if (!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    } else {
      Project project = findProjectByProjectNumber(projectNumber);
      project.assignProjectActivity(new ProjectActivity(title, startWeek, endWeek));
    }
  }

  public void setTimeBudget(String projectNumber, String title, Integer timeBudget) throws NotFoundException, OperationNotAllowedException {
    if (!isLoggedIn()) {
      throw new OperationNotAllowedException("Login krævet");
    } else {
      Project project = findProjectByProjectNumber(projectNumber);
      project.findProjectActivity(title).setTimeBudget(timeBudget);
    }
  }
}