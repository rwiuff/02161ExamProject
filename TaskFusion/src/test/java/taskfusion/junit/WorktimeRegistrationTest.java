package taskfusion.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.NotFoundException;
import taskfusion.helpers.DateHelper;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class WorktimeRegistrationTest {

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
}
