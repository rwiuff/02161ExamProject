package taskfusion.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.RegularActivity;
import taskfusion.exceptions.NotFoundException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.viewModels.RegularActivityViewModel;

public class RegularActivitySteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;
  private RegularActivityViewModel regularActivity;
  private List<RegularActivityViewModel> regularActivityList;
  boolean hasRegularActivity;

  public RegularActivitySteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
    this.taskFusion = taskFusion;
    this.errorMessageHolder = errorMessageHolder;
  }

  @When("the user creates the regular activity {string} with start week {string} and end week {string}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String title, String startWeek, String endWeek) {
    try {
      this.taskFusion.getEmployeeFacade().createRegularActivity(title, startWeek, endWeek);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the user has a regular activity with title {string} with start week {string} and end week {string}")
  public void theUserHasARegularActivityWithTitleWithStartWeekAndEndWeek(String title, String startWeek,
      String endWeek) throws NotFoundException {
    this.taskFusion.getEmployeeFacade().hasRegularActivity(title, startWeek, endWeek);
  }

  @Then("the regular activity with id {int} does not exist")
  public void the_regular_activity_with_id_does_not_exist(int id) throws NotFoundException {
    RegularActivity activity = EmployeeRepository.getInstance().findRegularActivityById(id);
    assertNull(activity);
  }

  @Then("a regular activity is returned with id {int}, title {string}, start week {string} and end week {string}")
  public void a_regular_activity_is_returned_with_id_title_start_week_and_end_week(int id, String title,
      String startWeek,
      String endWeek) {
    assertEquals(title, this.regularActivity.title);
    assertEquals(id, this.regularActivity.id);
    assertEquals(startWeek, this.regularActivity.startWeek);
    assertEquals(endWeek, this.regularActivity.endWeek);
  }

  @When("the user requests a regular activity with id {int}")
  public void the_user_requests_a_regular_activity_with_id(int id) {
    try {
      this.regularActivity = taskFusion.getEmployeeFacade().getRegularActivityById(id);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the regular activities list contains {int} items")
  public void the_regular_activities_list_contains_items(int amount) {
    assertEquals(amount, this.regularActivityList.size());
  }

  @When("the user requests a list of own regular activities")
  public void the_user_requests_a_list_of_own_regular_activities() {
    try {
      this.regularActivityList = taskFusion.getEmployeeFacade().getRegularActivities();
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user requests a regular activity {string} with start week {string} and end week {string}")
  public void the_user_requests_a_regular_activity_with_start_week_and_end_week(String title, String startWeek,
      String endWeek) throws NotFoundException {
    this.hasRegularActivity = EmployeeRepository.getInstance().findByInitials(taskFusion.getLoggedInUser().initials)
        .hasRegularActivity(title, startWeek, endWeek);
  }

  @Then("the user does not have such a regular activity")
  public void theUserDoesNotHaveSuchARegularActivity() {
    assertFalse(this.hasRegularActivity);
  }
}
