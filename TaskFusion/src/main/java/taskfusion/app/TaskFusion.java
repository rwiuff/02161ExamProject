package taskfusion.app;

import java.util.Calendar;
import taskfusion.exceptions.NotFoundException;
import taskfusion.facades.EmployeeFacade;
import taskfusion.facades.ProjectFacade;
import taskfusion.viewModels.EmployeeViewModel;

public class TaskFusion {

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
   * Authentification
   * ###########################
   */
  public EmployeeViewModel getLoggedInUser() {
    return loggedInUser;
  }

  public void login(String initials) throws NotFoundException {
    loggedInUser = employeeFacade.findEmployeeByInitials(initials);

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
   * FACADES
   * ###########################
   */

  public EmployeeFacade getEmployeeFacade() {
    return this.employeeFacade;
  }

  public ProjectFacade getProjectFacade() {
    return this.projectFacade;
  }

}