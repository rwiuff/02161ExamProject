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
import taskfusion.facades.EmployeeFacade;
import taskfusion.facades.ProjectFacade;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
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
   * REGULAR ACTIVITY facades
   * ###########################
   */


  

}