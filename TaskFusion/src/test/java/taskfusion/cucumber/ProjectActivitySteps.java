package taskfusion.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;

public class ProjectActivitySteps {
  private ErrorMessageHolder errorMessageHolder;
  private TaskFusion taskFusion;

  public ProjectActivitySteps(TaskFusion taskFusion, ErrorMessageHolder errorMessageHolder) {
    this.errorMessageHolder = errorMessageHolder;
    this.taskFusion = taskFusion;
  }

  @Given("a project with title {string} with project number {int} exists in the application")
  public void aProjectWithTitleWithProjectNumberExistsInTheApplication(String string, Integer int1) {
    try {
      this.taskFusion.createProject(string);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user assigns the project activity {string} to project {int}")
  public void theUserAssignsTheProjectActivityToProject(String string, Integer int1) {
    try {
      this.taskFusion.createProjectActivity(int1, string, 0, 1);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project with the project number {int} has a project activity titled {string}")
  public void theProjectWithTheProjectNumberHasAProjectActivityTitled(Integer int1, String string) {
    try {
      boolean result = this.taskFusion.findProjectByProjectNumber(String.valueOf(int1)).hasProjectActivity(string);
      assertTrue(result);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user sets the time budget to {int} hours on the project activity with the title {string} and project number {int}")
  public void the_user_sets_the_time_budget_to_hours_on_the_project_activity_with_the_title_and_project_number(
      int hours, String activityTitle, int projectNumber) {
    try {
      this.taskFusion.setTimeBudget(projectNumber, activityTitle, hours);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with the title {string} and project number {int} has a time budget of {int} hours")
  public void the_project_activity_with_the_title_and_project_number_has_a_time_budget_of_hours(String activityTitle,
      int projectNumber, int timeBudget) {
    try {
      assertEquals(timeBudget, this.taskFusion.getTimeBudget(projectNumber, activityTitle));
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }
}
