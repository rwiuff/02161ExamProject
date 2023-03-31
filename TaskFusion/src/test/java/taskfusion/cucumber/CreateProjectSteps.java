package taskfusion.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import taskfusion.exceptions.NotFoundException;
import taskfusion.helpers.MockDateHolder;

public class CreateProjectSteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;
  private MockDateHolder mockDateHolder;

  public CreateProjectSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion,
      MockDateHolder mockDateHolder) {
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
  public void theYearIs(Integer year) {
    assertEquals(this.mockDateHolder.getYear(), year);
  }

  @When("the user creates a project with title {string}")
  public void theUserCreatesAProjectWithTitle(String projectTitle) {
    try {
      this.taskFusion.createProject(projectTitle);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("a project with title {string} with project number {string} exists in the application")
  public void aProjectWithTitleWithProjectNumberExistsInTheApplication(String projectTitle, String projectNumber) {
    assertEquals(1, this.taskFusion.projectRepo.projects.size());
    Set<String> p = this.taskFusion.projectRepo.projects.keySet();
    System.out.println(p);
    // Project p = this.taskFusion.projectRepo.getProject(projectNumber);
    // assertNotNull(p);
    // assertEquals(projectTitle,p.getProjectTitle());
  }

  @Given("a project with title {string} with project number {int} has been created in the application")
  public void a_project_with_title_with_project_number_has_been_created_in_the_application(String projectTitle,
      int projectID) {
    try {
      this.taskFusion.createProject(projectTitle);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user sets customer {string} on project {string}")
  public void the_user_sets_customer_on_project(String customer, String projectID) {
    taskFusion.assignCustomer(projectID, customer);
  }

  @Then("the project {string} has customer {string}")
  public void the_project_has_customer(String projectID, String customer) {
    assertEquals(customer, taskFusion.projectRepo.getProject(projectID).getCustomer());
  }

  @When("the user sets the start week to {int} on {string}")
  public void the_user_sets_the_start_week_to_on(int start, String projectID) {
    taskFusion.projectRepo.getProject(projectID).setStartWeek(start);
  }

  @Then("the project has start week {int} on {string}")
  public void the_project_has_start_week_on(int start, String projectID) {
    assertEquals(start, taskFusion.projectRepo.getProject(projectID).getStartWeek());
  }

}
