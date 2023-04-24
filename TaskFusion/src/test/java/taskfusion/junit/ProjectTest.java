package taskfusion.junit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.Project;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.ProjectViewModel;

public class ProjectTest {
  @BeforeEach
  public void resetSingletons() {
    SingletonHelpers.resetSingletons();
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
