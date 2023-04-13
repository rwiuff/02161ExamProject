package taskfusion.cucumber;

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
}
