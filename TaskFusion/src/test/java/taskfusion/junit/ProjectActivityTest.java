package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.Employee;
import taskfusion.domain.ProjectActivity;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.ProjectActivityViewModel;

public class ProjectActivityTest { 

  @BeforeEach
  public void resetSingletons() {
    SingletonHelpers.resetSingletons();
  }


  @Test
  public void testProjectActivityViewModel() throws InvalidPropertyException, NotFoundException, OperationNotAllowedException, ExhaustedOptionsException {

    String title = "Planlægning";
    String startWeek = "2301";
    String endWeek = "2306";
    Employee mila = new Employee("Michael", "Laudrup");

    ProjectActivity model = new ProjectActivity(title,startWeek,endWeek);
    ProjectActivityViewModel viewModel = model.toViewModel();

    //Test for initial creation
    assertEquals(title, viewModel.title);
    assertEquals(startWeek, viewModel.startWeek);
    assertEquals(endWeek, viewModel.endWeek);
    assertEquals(0, viewModel.timeBudget);
    assertEquals(0, viewModel.worktimeRegistrations.size());
    assertEquals(0, viewModel.totalWorktime);

    //test when adding time budget
    model.setTimeBudget(100);
    viewModel = model.toViewModel();
    assertEquals(100, viewModel.timeBudget);

    //Test adding worktimeregistrations
    Double time1 = 10.5;
    double time2 = 3;

    model.registerWorkTime(mila, new DateServer().getDate(), time1);
    model.registerWorkTime(mila, new DateServer().getDate(), time2);
    viewModel = model.toViewModel();
    assertEquals(2, viewModel.worktimeRegistrations.size());
    assertEquals((time1 + time2), viewModel.totalWorktime);

  }

  @Test
  public void testProjectActivityListFromModels() throws InvalidPropertyException {

    List<ProjectActivity> models = new ArrayList<>();
    
    models.add(new ProjectActivity("TaskFusion", "2301", "2306"));
    models.add(new ProjectActivity("Half-Life 3", "2109", "2110"));
    models.add(new ProjectActivity("Cure cancer", "2901", "3606"));

    List<ProjectActivityViewModel> viewModels = ProjectActivityViewModel.listFromModels(models);
    assertEquals(models.size(), viewModels.size());

  }


}
