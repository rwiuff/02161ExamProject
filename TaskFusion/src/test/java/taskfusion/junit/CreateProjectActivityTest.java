package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.MockDateHolder;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;

public class CreateProjectActivityTest {
  private TaskFusion taskFusion;
  private MockDateHolder mockDateHolder;
  private Project project;
  private Exception exception;

  @BeforeEach
  public void initializeTests() throws InvalidPropertyException, ExhaustedOptionsException,
      OperationNotAllowedException, NotFoundException, AlreadyExistsException {
    SingletonHelpers.resetSingletons();
    this.taskFusion = new TaskFusion();
    new MockDateHolder(taskFusion);
    this.mockDateHolder = new MockDateHolder(taskFusion);
    this.mockDateHolder.setYear(2021);

    this.taskFusion.getEmployeeFacade().registerEmployee("Michael", "Laudrup");
    this.taskFusion.getEmployeeFacade().registerEmployee("Brian", "Laudrup");

    taskFusion.login("mila");
    this.project = this.taskFusion.getProjectFacade().createProject("TaskFusion");
  }

  @Test
  public void testCreateProjectActivity_A1()
      throws AlreadyExistsException, OperationNotAllowedException, InvalidPropertyException, NotFoundException {
    this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());

    assertTrue(this.project.hasProjectActivity("Planlægning"));
  }

  @Test
  public void testCreateProjectActivity_B1() {

    // Create a existing project
    try {
      this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());
    } catch (Exception e) {
      this.exception = e;
    }

    assertNull(exception);

    try {
      this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());
    } catch (Exception e) {
      this.exception = e;
    }

    assertEquals("Projekt aktivitet findes allerede", this.exception.getMessage());

    assertTrue(this.project.hasProjectActivity("Planlægning"));
  }

  @Test
  public void testCreateProjectActivity_C1() throws AlreadyExistsException, NotFoundException {

    //make user the project leader
    this.project.setProjectLeader(getLoggedInUserModel());
    assertEquals("mila",this.project.getProjectLeader().getInitials());

    try {
      this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());
    } catch (Exception e) {
      this.exception = e;
    }

    assertNull(exception);
    assertTrue(this.project.hasProjectActivity("Planlægning"));
  }

  @Test
  public void testCreateProjectActivity_D1() throws AlreadyExistsException, NotFoundException {

    //Create a existing project
    try {
      this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());
    } catch (Exception e) {
      this.exception = e;
    }
    assertNull(exception);
              
    //make user the project leader
    this.project.setProjectLeader(getLoggedInUserModel());
    assertEquals("mila",this.project.getProjectLeader().getInitials());

    try {
      this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());
    } catch (Exception e) {
      this.exception = e;
    }

    assertEquals("Projekt aktivitet findes allerede", this.exception.getMessage());
  }

  @Test
  public void testCreateProjectActivity_E1() throws AlreadyExistsException, NotFoundException {
              
    //make user the project leader
    this.project.setProjectLeader(EmployeeRepository.getInstance().findByInitials("brla"));
    assertEquals("brla",this.project.getProjectLeader().getInitials());

    try {
      this.project.createProjectActivity("Planlægning", "2101", "2103", getLoggedInUserModel());
    } catch (Exception e) {
      this.exception = e;
    }

    assertEquals("Kun projektlederen kan oprette en projekt aktivitet for dette projekt", this.exception.getMessage());
  }

  /**
   * ###########################
   * Helper methods
   * ###########################
   */
  private Employee getLoggedInUserModel() throws NotFoundException {
    return EmployeeRepository.getInstance().findByInitials(taskFusion.getLoggedInUser().initials);
  }

}
