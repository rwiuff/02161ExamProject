package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.ProjectActivity;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.ProjectActivityViewModel;

public class ProjectActivityTest {

  @BeforeEach
  public void resetSingletons() {
    SingletonHelpers.resetSingletons();
  }


  @Test
  public void testProjectActivityViewModel() throws InvalidPropertyException {

    String title = "Planlægning";
    String startWeek = "2301";
    String endWeek = "2306";
    String initials = "mila";

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

    model.registerWorkTime(initials, new DateServer().getDate(), time1);
    model.registerWorkTime(initials, new DateServer().getDate(), time2);
    viewModel = model.toViewModel();
    assertEquals(2, viewModel.worktimeRegistrations.size());
    assertEquals((time1 + time2), viewModel.totalWorktime);

  }
}
