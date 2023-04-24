package taskfusion.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import taskfusion.app.TaskFusion;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.facades.ProjectFacade;
import taskfusion.helpers.MockDateHolder;
import taskfusion.app.DateServer;
import taskfusion.domain.Project;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.ProjectViewModel;

import taskfusion.persistency.ProjectRepository;

public class ProjectTest {
  private TaskFusion taskFusion;
  private ProjectFacade projectFacade;
  private MockDateHolder mockDateHolder;

  @BeforeEach
  public void initializeTests() throws InvalidPropertyException, ExhaustedOptionsException, NotFoundException {
    SingletonHelpers.resetSingletons();
    assertTrue(ProjectRepository.getInstance().all().size() == 0);
    this.taskFusion = new TaskFusion();
    this.projectFacade = new ProjectFacade(taskFusion);
    this.mockDateHolder = new MockDateHolder(taskFusion);
    this.taskFusion.getEmployeeFacade().registerEmployee("Mette", "Frederiksen");
    taskFusion.login("mefr");
  }

  @Test 
  public void testGenerateProjectNumberTwoProjects() throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException, ExhaustedOptionsException {
    mockDateHolder.setYear(2023);
    projectFacade.createProject("2023_project");
    projectFacade.createProject("2023_project_1");
    projectFacade.createProject("2023_project_2");
    assertNotNull(projectFacade.findProjectByProjectNumber("23003"));

    mockDateHolder.setYear(2002);
    projectFacade.createProject("2002_project");
    projectFacade.createProject("2002_project_1");
    projectFacade.createProject("2002_project_2");
    assertNotNull(projectFacade.findProjectByProjectNumber("02003"));
   
  }

  @Test 
  public void testGenerateProjectNumberOneProject() throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
    mockDateHolder.setYear(2022);
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("22001") == null);
    } catch (Exception e) {
    }
    projectFacade.createProject("2022_project");
    assertTrue(projectFacade.findProjectByProjectNumber("22001") != null);

    mockDateHolder.setYear(2001);
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("01001") == null);
    } catch (Exception e) {
    }
    projectFacade.createProject("2001_project");
    assertTrue(projectFacade.findProjectByProjectNumber("01001") != null);
    
    mockDateHolder.setYear(2023);
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("23001") == null);
    } catch (Exception e) {
    }
    projectFacade.createProject("2023_project");
    assertTrue(projectFacade.findProjectByProjectNumber("23001") != null);
  }

  @Test 
  public void testGenerateProjectNumberZeroProjects2001() throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
    mockDateHolder.setYear(2001);
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("01001") == null);
    } catch (Exception e) {
    }
    projectFacade.createProject("2001_project");
    assertTrue(projectFacade.findProjectByProjectNumber("01001") != null);
  }

  @Test 
  public void testGenerateProjectNumberZeroProjects2023() throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
    mockDateHolder.setYear(2023);
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("23001") == null);
    } catch (Exception e) {
    }
    projectFacade.createProject("2023_project");
    assertTrue(ProjectRepository.getInstance().all().containsKey("23001"));
  }

  @Test
  public void testProjectViewModel() {

    String title = "TaskFusion";
    Calendar date = new DateServer().getDate();

    Project model = new Project(title,date);
    ProjectViewModel viewModel = model.toViewModel();

    //test when constructing project
    assertEquals(model.getProjectNumber(), viewModel.projectNumber);
    assertEquals(title, viewModel.projectTitle);
    assertEquals(null, viewModel.customer);
    assertEquals(null, viewModel.projectLeaderFullName);
    assertEquals(0, viewModel.projectActivities.size());
    assertEquals(0, viewModel.reports.size());
  }

  @Test
  public void testProjectListFromModels()  {

    Calendar date = new DateServer().getDate();

    List<Project> models = new ArrayList<>();
    
    models.add(new Project("TaskFusion",date));
    models.add(new Project("Half-Life 3", date));

    List<ProjectViewModel> viewModels = ProjectViewModel.listFromModels(models);
    assertEquals(models.size(), viewModels.size());

  }

}
