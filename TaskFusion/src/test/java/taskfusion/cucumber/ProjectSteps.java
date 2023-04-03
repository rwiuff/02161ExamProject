package taskfusion.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.MockDateHolder;

public class ProjectSteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;
  private MockDateHolder mockDateHolder;

  public ProjectSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion,
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
    this.mockDateHolder.setYear(year);
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

    try {
      Project p = this.taskFusion.projectRepo.getProject(projectNumber);
      assertNotNull(p);
      assertEquals(projectTitle, p.getProjectTitle());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Given("a project with title {string} has been created in the application")
  public void a_project_with_title_with_project_number_has_been_created_in_the_application(String projectTitle) {
    try {
      System.out.println(taskFusion.projectRepo.projects.size());
      this.taskFusion.createProject(projectTitle);
      System.out.println(taskFusion.projectRepo.projects.size());
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
    try {
      assertEquals(customer, taskFusion.projectRepo.getProject(projectID).getCustomer());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user sets the start week to {int} on {string}")
  public void the_user_sets_the_start_week_to_on(int start, String projectID) {
    try {
      taskFusion.projectRepo.getProject(projectID).setStartWeek(start);
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project has start week {int} on {string}")
  public void the_project_has_start_week_on(int start, String projectID) {
    try {
      assertEquals(start, taskFusion.projectRepo.getProject(projectID).getStartWeek());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("{string} takes the role as project leader on project {string}")
  public void takes_the_role_as_project_leader_on_project(String initials, String projectNumber) {
    try {
      taskFusion.projectRepo.getProject(projectNumber).setProjectLeader(taskFusion.employeeRepo.findEmployee(initials));
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("{string} is the project leader on project {string}")
  public void is_the_project_leader_on_project(String initials, String projectNumber) {
    try {
      assertEquals(initials, taskFusion.projectRepo.getProject(projectNumber).getProjectLeader().getInitials());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user assigns {string} to the project with id {string}")
  public void assigns_to_the_project_with_id(String employeeInitials, String projectID) {
    try {
      taskFusion.assignEmployeeToProject(projectID, employeeInitials);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the employee {string} is assigned to the project titled {string}")
  public void the_employee_is_assigned_to_the_project_titled(String initials, String projectTitle) {
    Project project;
    try {
      project = taskFusion.projectRepo.getByTitle(projectTitle);
      Employee employee = project.getAssignedEmployee(initials);
      assertEquals(initials, employee.getInitials());
      assertEquals(projectTitle, project.getProjectTitle());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

}
