package taskfusion.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import taskfusion.helpers.SingletonHelpers;

import taskfusion.persistency.ProjectRepository;

public class ProjectTest {
  private TaskFusion taskFusion;
  private ProjectFacade projectFacade;
  private MockDateHolder mockDateHolder;
  @BeforeEach
  public void resetSingletons() throws InvalidPropertyException, ExhaustedOptionsException, NotFoundException {
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
    mockDateHolder.setYear(2001);

    

    projectFacade.createProject("2001_project");
    projectFacade.createProject("2001_project_2");
    
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("01003") == null);
    } catch (Exception e) {
    }

    projectFacade.createProject("2001_project_3");
    assertTrue(projectFacade.findProjectByProjectNumber("01003") != null);
    
    mockDateHolder.setYear(2023);
    projectFacade.createProject("2023_project");
    projectFacade.createProject("2023_project_2");
    try {
      assertFalse(projectFacade.findProjectByProjectNumber("23003") == null);
    } catch (Exception e) {
    }
    projectFacade.createProject("2023_project_3");
    assertTrue(projectFacade.findProjectByProjectNumber("23003") != null);
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
}
