package taskfusion.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.DateHelper;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class WorktimeRegistrationTest {

  private Exception exception;

  @BeforeEach
  public void resetSingletons() {
    SingletonHelpers.resetSingletons();
  }


  @Test
  public void testWorktimeRegistrationViewModel() throws NotFoundException {

    Calendar date = new DateServer().getDate();
    double worktime = 10.5;
    String initials = "mila";

    WorktimeRegistration model = new WorktimeRegistration(initials,date,worktime);
    WorktimeRegistrationViewModel viewModel = model.toViewModel();

    //Test for initial creation
    assertEquals(model.getId(), viewModel.id);
    assertEquals(initials, viewModel.initials);
    assertEquals(worktime, viewModel.time);
    assertTrue(DateHelper.compareDates(viewModel.date, model.getDate()));
    assertEquals(DateHelper.getDateAsString(model.getDate()), viewModel.dateString);
  }

  @Test
  public void testWorktimeRegistrationsListFromModels() throws NotFoundException  {

    Calendar date = new DateServer().getDate();

    List<WorktimeRegistration> models = new ArrayList<>();
    
    models.add(new WorktimeRegistration("mila",date,10.0 ));
    models.add(new WorktimeRegistration("mila",date,3));

    List<WorktimeRegistrationViewModel> viewModels = WorktimeRegistrationViewModel.listFromModels(models);
    assertEquals(models.size(), viewModels.size());
  }

  

  @Test
  public void dbcTestFindWorktimeRegistrationById() throws NotFoundException, OperationNotAllowedException, InvalidPropertyException, ExhaustedOptionsException, AlreadyExistsException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

    Calendar date = new DateServer().getDate();
    ProjectRepository.getInstance().create("Demo 1", date);
    EmployeeRepository.getInstance().create("Michael", "Laudrup");
    Employee employee = EmployeeRepository.getInstance().findByInitials("mila");
    Project project = ProjectRepository.getInstance().findByProjectNumber("23001");
    
    String startWeek = "2301";
    String endWeek = "2305";

    project.createProjectActivity("Test", startWeek, endWeek, employee);

    double worktime1 = 7.5;
    double worktime2 = 10.5;
    double worktime3 = 10.5;

    // Registering 3 different worktimes for the activity Test
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime1);
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime2);
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime3);

    // Have to access private method using reflection
    // Used for dbcTestFindWorktimeRegistrationById()
    Method method = ProjectRepository.class.getDeclaredMethod("allWorktimeRegistrations");
    method.setAccessible(true);
    
    List<WorktimeRegistration> worktimeRegistrations = (List<WorktimeRegistration>) method.invoke(ProjectRepository.getInstance());
    
    int id = 2;

    // precondition
    assertTrue(worktimeRegistrations.size() > 0 && id == (int) id && 0 < id && id <= worktimeRegistrations.size());

    // Executing the method itself
    WorktimeRegistration worktimeRegistration = ProjectRepository.getInstance().findWorktimeRegistrationById(id);

    // postcondition
    // NOTE: This should be checked inside the method itself, but as an alternative we check
    // it afterwards where the result is effectively the same
    assertTrue(worktimeRegistration.getId().equals(id));

  }

  @Test
  public void testFindWorktimeRegistrationByIdWithNoRegistration() throws NotFoundException, InvalidPropertyException, ExhaustedOptionsException, AlreadyExistsException, OperationNotAllowedException {
    
    Calendar date = new DateServer().getDate();
    ProjectRepository.getInstance().create("Demo 1", date);
    EmployeeRepository.getInstance().create("Michael", "Laudrup");
    Employee employee = EmployeeRepository.getInstance().findByInitials("mila");
    Project project = ProjectRepository.getInstance().findByProjectNumber("23001");
    
    String startWeek = "2301";
    String endWeek = "2305";

    project.createProjectActivity("Test", startWeek, endWeek, employee);

    // Arbitrary id
    int id = 1;
    try {
      ProjectRepository.getInstance().findWorktimeRegistrationById(id);
    } catch (Exception e) {
      this.exception = e;
    }
    
    assertEquals("Ukendt tidsregistrering", this.exception.getMessage());
    
  }

  @Test
  public void testFindWorktimeRegistrationByIdWrongInput() throws NotFoundException, InvalidPropertyException, ExhaustedOptionsException, AlreadyExistsException, OperationNotAllowedException {
    
    Calendar date = new DateServer().getDate();
    ProjectRepository.getInstance().create("Demo 1", date);
    EmployeeRepository.getInstance().create("Michael", "Laudrup");
    Employee employee = EmployeeRepository.getInstance().findByInitials("mila");
    Project project = ProjectRepository.getInstance().findByProjectNumber("23001");
    
    String startWeek = "2301";
    String endWeek = "2305";

    project.createProjectActivity("Test", startWeek, endWeek, employee);

    double worktime = 5.0;

    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime);

    // Arbitrary id
    int id = 2;
    try {
      ProjectRepository.getInstance().findWorktimeRegistrationById(id);
    } catch (Exception e) {
      this.exception = e;
    }
    
    assertEquals("Ukendt tidsregistrering", this.exception.getMessage());
    
  }

  @Test
  public void testFindWorktimeRegistrationByIdWithRightInputAndNonEmptyList() throws NotFoundException, InvalidPropertyException, ExhaustedOptionsException, AlreadyExistsException, OperationNotAllowedException {
    
    Calendar date = new DateServer().getDate();
    ProjectRepository.getInstance().create("Demo 1", date);
    EmployeeRepository.getInstance().create("Michael", "Laudrup");
    Employee employee = EmployeeRepository.getInstance().findByInitials("mila");
    Project project = ProjectRepository.getInstance().findByProjectNumber("23001");
    
    String startWeek = "2301";
    String endWeek = "2305";

    project.createProjectActivity("Test", startWeek, endWeek, employee);

    double worktime1 = 5.0;
    double worktime2 = 2.5;
    double worktime3 = 4;
    double worktime4 = 12.5;
    double worktime5 = 20;

    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime1);
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime2);
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime3);
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime4);
    project.findProjectActivity("Test").registerWorkTime(employee, date, worktime5);

    // Arbitrary id
    int id = 4;

    WorktimeRegistration worktimeRegistration = ProjectRepository.getInstance().findWorktimeRegistrationById(id);

    assertEquals(worktimeRegistration.getId(), id);
    
  }

}
