package taskfusion.cucumber;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.RegularActivity;

public class RegularActivitySteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;
  private List<RegularActivity> regularActivities = new ArrayList<RegularActivity>();

  public RegularActivitySteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
    this.taskFusion = taskFusion;
    this.errorMessageHolder = errorMessageHolder;
  }

  @When("the user creates the regular activity {string} with start week {int} and end week {int}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String string, Integer int1, Integer int2) {
    try {
      this.taskFusion.getEmployeeFacade().createRegularActivity(string, int1, int2);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the user has a regular activity with title {string} with start week {int} and end week {int}")
  public void theUserHasARegularActivityWithTitleWithStartWeekAndEndWeek(String string, Integer int1, Integer int2) {
    this.taskFusion.getEmployeeFacade().hasRegularActivity(string, int1, int2);
  }

  @When("the user creates the regular activity {string} with start week {string} and end week {int}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String string, String string2, Integer int1) {
    try {
      this.taskFusion.getEmployeeFacade().createRegularActivity(string, null, int1);
      ;
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user creates the regular activity {string} with start week {int} and end week {string}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String string, Integer int1, String string2) {
    try {
      this.taskFusion.getEmployeeFacade().createRegularActivity(string, int1, null);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user with initials {string} requests a list of their regular activities")
  public void theUserRequestsAListOfTheirRegularActivities(String initials) {
    this.regularActivities = this.taskFusion.getEmployeeFacade().employeeRepo.findByInitials(initials).getRegularActivities();
  }

  @Then("the list of regular activities has size {int}")
  public void theListOfRegularActivitiesHasSize(Integer int1) {
    assertTrue(this.regularActivities.size() == 3);
  }
}
