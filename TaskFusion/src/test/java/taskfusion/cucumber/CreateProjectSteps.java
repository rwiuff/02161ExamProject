package taskfusion.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import taskfusion.exceptions.NotFoundException;
import taskfusion.helpers.MockDateHolder;

public class CreateProjectSteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;
  private MockDateHolder mockDateHolder;

  public CreateProjectSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion, MockDateHolder mockDateHolder) {
    this.taskFusion = taskFusion;
    this.errorMessageHolder = errorMessageHolder;
    this.mockDateHolder = mockDateHolder;
  }

  @Given("the user logs in with initials {string}")
  public void theUserLogsInWithInitials(String string) throws NotFoundException {
    try {
      this.taskFusion.login(string);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
}

  @Given("the year is {int}")
  public void theYearIs(Integer int1) {
    assertEquals(this.mockDateHolder.getYear(), int1);
  }

  @When("the user creates a project with title {string}")
  public void theUserCreatesAProjectWithTitle(String string) {
    this.taskFusion.createProject(string);
  }

  @Then("a project with title {string} with project number {int} exists in the application")
  public void aProjectWithTitleWithProjectNumberExistsInTheApplication(String string, int int1) {
    assertNotNull(this.taskFusion.findProject(string, int1));
  }
}
