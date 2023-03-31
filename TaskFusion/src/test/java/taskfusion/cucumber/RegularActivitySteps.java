package taskfusion.cucumber;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;

public class RegularActivitySteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;

  public RegularActivitySteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
    this.taskFusion = taskFusion;
    this.errorMessageHolder = errorMessageHolder;
  }

  @Given("the application has a registered employee with first name {string}, last name {string} and initials {string}")
  public void theApplicationHasARegisteredEmployeeWithFirstNameLastNameAndInitials(String string, String string2,
      String string3) {
    try {
      this.taskFusion.registerEmployee(string, string2);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }

    assertNotNull(this.taskFusion.findEmployee(string3));
  }

  @When("the user creates the regular activity {string} with start week {int} and end week {int}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String string, Integer int1, Integer int2) {
    try {
      this.taskFusion.createRegularActivity(string, int1, int2);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the user has a regular activity with title {string} with start week {int} and end week {int}")
  public void theUserHasARegularActivityWithTitleWithStartWeekAndEndWeek(String string, Integer int1, Integer int2) {
    this.taskFusion.hasRegularActivity(string, int1, int2);
  }

  @When("the user creates the regular activity {string} with start week {string} and end week {int}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String string, String string2, Integer int1) {
    try {
      this.taskFusion.createRegularActivity(string, null, int1);
      ;
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user creates the regular activity {string} with start week {int} and end week {string}")
  public void theUserCreatesTheRegularActivityWithStartWeekAndEndWeek(String string, Integer int1, String string2) {
    try {
      this.taskFusion.createRegularActivity(string, int1, null);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }
}
