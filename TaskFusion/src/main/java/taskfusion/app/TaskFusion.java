package taskfusion.app;

import java.util.HashMap;
import java.util.Map;

import taskfusion.domain.Employee;
import taskfusion.domain.RegularActivity;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.exceptions.ShouldNotHappenException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class TaskFusion {

  private DateServer dateServer = new DateServer();
  private Employee loggedInUser;

  public ProjectRepository projectRepo = ProjectRepository.getInstance();
  public EmployeeRepository employeeRepo = EmployeeRepository.getInstance();

  public void registerEmployee(String firstName, String lastName)
      throws InvalidPropertyException, AlreadyExistsException, ShouldNotHappenException {
        employeeRepo.registerEmployee(firstName, lastName);
  }

  public Employee findEmployee(String initials) {
    return employeeRepo.findEmployee(initials);
  }

  public Employee getLoggedInUser() {
    return loggedInUser;
  }

  public void login(String initials) throws NotFoundException {
    loggedInUser = findEmployee(initials);

    if (loggedInUser == null) {
      throw new NotFoundException("Ukendt medarbejder");
    }
  }

  public boolean isLoggedIn() {
    //return true;
    return loggedInUser != null;
  }

  public void logout() {
    loggedInUser = null;
  }

  public void createProject(String projectTitle) throws OperationNotAllowedException, InvalidPropertyException {
    if (!isLoggedIn()) {
      throw new OperationNotAllowedException("Kun medarbejdere kan oprette et projekt");
    } else {
      projectRepo.createProject(projectTitle, this.dateServer.getDate());
    }
  }

  public void setDateServer(DateServer dateServer) {
    this.dateServer = dateServer;
  }

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

  public void assignCustomer(String projectID, String customer) {
    projectRepo.projects.get(projectID).setCustomer(customer);
  }
}