package taskfusion.cucumber;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;

public class ProjectActivitySteps {
  private ErrorMessageHolder errorMessageHolder;
  private TaskFusion taskFusion;

  public ProjectActivitySteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
    this.errorMessageHolder = errorMessageHolder;
    this.taskFusion = taskFusion;
  }

  @Given("the user assigns the project activity {string} to project {string} with startWeek {int} and endWeek {int}")
  public void theUserAssignsTheProjectActivityToProjectWithStartWeekAndEndWeek(String string, String string2, Integer int1, Integer int2) {
    try {
      this.taskFusion.createProjectActivity(string2, string, int1, int2);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project with the project number {string} has a project activity titled {string}")
  public void theProjectWithTheProjectNumberHasAProjectActivityTitled(String string1, String string2) {
    try {
      assertNotNull(this.taskFusion.findProjectByProjectNumber(string1).findProjectActivity(string2));
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Given("no one is logged in")
  public void nooneIsLoggedIn() {
    this.taskFusion.logout();
  }

  @When("the user sets the time budget to {int} hours on the project activity with the title {string} and project number {string}")
  public void theUserSetsTheTimeBudgetToHoursOnTheProjectActivityWithTheTitleAndProjectNumber(Integer int1, String string1, String string2) {
    try {
      this.taskFusion.setTimeBudget(string2, string1, int1);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with the title {string} and project number {string} has a time budget of {int} hours")
  public void theProjectActivityWithTheTitleAndProjectNumberHasATimeBudgetOfHours(String string, String string2, Integer int2) {
    try {
      assertEquals(int2, this.taskFusion.findProjectByProjectNumber(string2).findProjectActivity(string).getTimeBudget());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with title {string} and project number {string} has end week {int}")
  public void theProjectActivityWithTitleAndProjectNumberHasEndWeek(String string, String string2, Integer int1) {
    try {
      assertEquals(int1, this.taskFusion.findProjectByProjectNumber(string2).findProjectActivity(string).getEndWeek());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with the title {string} and project number {string} has start week {int}")
  public void theProjectActivityWithTheTitleAndProjectNumberHasStartWeek(String string, String string2, Integer int1) {
    try {
      assertEquals(int1, this.taskFusion.findProjectByProjectNumber(string2).findProjectActivity(string).getStartWeek());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }
}
