package taskfusion.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

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

  @Then("a project with title {string} with project number {int} exists in the application")
  public void aProjectWithTitleWithProjectNumberExistsInTheApplication(String projectTitle, int projectNumber) {

    assertEquals(1, this.taskFusion.projectRepo.projects.size());

    for (Map.Entry<String, Project> entry : this.taskFusion.projectRepo.projects.entrySet()) {
      assertEquals(projectNumber, entry.getKey());


    }


    Project pj = this.taskFusion.projectRepo.projects.get(projectNumber + "");
    assertNotNull(pj);


    //Project p = this.taskFusion.projectRepo.findProject(projectNumber);
    //assertNotNull(p);
    //assertEquals(projectTitle,p.getProjectTitle());
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

  @When("the user sets customer {string} on project {int}")
  public void the_user_sets_customer_on_project(String customer, int projectID) {
    taskFusion.assignCustomer(projectID, customer);
  }

  @Then("the project {int} has customer {string}")
  public void the_project_has_customer(int projectID, String customer) {
    assertEquals(customer, taskFusion.projectRepo.findProject(projectID).getCustomer());
  }

  @When("the user sets the start week to {int} on {int}")
  public void the_user_sets_the_start_week_to_on(int start, int projectID) {
    taskFusion.projectRepo.findProject(projectID).setStartWeek(start);
  }

  @Then("the project has start week {int} on {int}")
  public void the_project_has_start_week_on(int start, int projectID) {
    assertEquals(start, taskFusion.projectRepo.findProject(projectID).getStartWeek());
  }

}
