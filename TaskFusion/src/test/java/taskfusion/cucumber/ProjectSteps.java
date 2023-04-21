package taskfusion.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import taskfusion.exceptions.NotFoundException;
import taskfusion.helpers.MockDateHolder;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.EmployeeViewModel;

public class ProjectSteps {
  private TaskFusion taskFusion;
  private ErrorMessageHolder errorMessageHolder;
  private MockDateHolder mockDateHolder;
  private List<EmployeeViewModel> assignedEmployees = new ArrayList<EmployeeViewModel>();

  public ProjectSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion,
      MockDateHolder mockDateHolder) {
    this.taskFusion = taskFusion;
    this.errorMessageHolder = errorMessageHolder;
    this.mockDateHolder = mockDateHolder;
  }

  @Given("the year is {int}")
  public void theYearIs(Integer year) {
    this.mockDateHolder.setYear(year);
  }

  @When("the user creates a project with title {string}")
  public void theUserCreatesAProjectWithTitle(String projectTitle) {
    try {
      taskFusion.getProjectFacade().createProject(projectTitle);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("a project with title {string} with project number {string} exists in the application")
  public void aProjectWithTitleWithProjectNumberExistsInTheApplication(String projectTitle, String projectNumber) {

    try {
      Project p = ProjectRepository.getInstance().findByProjectNumber(projectNumber);
      assertNotNull(p);
      assertEquals(projectTitle, p.getProjectTitle());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user sets customer {string} on project {string}")
  public void the_user_sets_customer_on_project(String customer, String projectNumber) {
    try {
      taskFusion.getProjectFacade().assignCustomerToProject(projectNumber, customer);
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project {string} has customer {string}")
  public void the_project_has_customer(String projectNumber, String customer) {
    try {
      assertEquals(customer, ProjectRepository.getInstance().findByProjectNumber(projectNumber).getCustomer());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user sets the start week to {int} on {string}")
  public void the_user_sets_the_start_week_to_on(int start, String projectNumber) {
    try {
      ProjectRepository.getInstance().findByProjectNumber(projectNumber).setStartWeek(start);
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project has start week {int} on {string}")
  public void the_project_has_start_week_on(int start, String projectNumber) {
    try {
      assertEquals(start, ProjectRepository.getInstance().findByProjectNumber(projectNumber).getStartWeek());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user takes the role as project leader on project {string}")
  public void the_user_takes_the_role_as_project_leader_on_project(String projectNumber) {
    try {
      taskFusion.getProjectFacade().takeProjectLeaderRole(projectNumber);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("{string} is the project leader on project {string}")
  public void is_the_project_leader_on_project(String initials, String projectNumber) {
    try {
      assertEquals(initials,
          ProjectRepository.getInstance().findByProjectNumber(projectNumber).getProjectLeader().getInitials());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user assigns {string} to the project {string}")
  public void assigns_to_the_project(String employeeInitials, String projectNumber) {
    try {
      taskFusion.getProjectFacade().assignEmployeeToProject(projectNumber, employeeInitials);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the employee {string} is assigned to the project {string}")
  public void the_employee_is_assigned_to_the_project(String initials, String projectNumber) {
    Project project;
    try {
      project = ProjectRepository.getInstance().findByProjectNumber(projectNumber);
      Employee employee = project.getAssignedEmployee(initials);
      assertEquals(initials, employee.getInitials());
      assertEquals(projectNumber, project.getProjectNumber());
    } catch (NotFoundException e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("there is {int} projects in the application")
  public void there_is_projects_in_the_application(int i) {
    assertEquals(i, ProjectRepository.getInstance().all().size());
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("the project {string} is an internal project")
  public void the_project_is_an_internal_project(String projectNumber) throws NotFoundException {
    assertTrue(ProjectRepository.getInstance().findByProjectNumber(projectNumber).isInternal());
  }

  @Then("the project {string} is an external project")
  public void the_project_is_an_external_project(String projectNumber) throws NotFoundException {
    assertFalse(ProjectRepository.getInstance().findByProjectNumber(projectNumber).isInternal());
  }

  @When("the user requests a list of employees assigned to the project with project number {string}")
  public void theUserRequestsAListOfEmployeesAssignedToTheActivityTitledInTheProjectWithProjectNumber(
      String projectNumber) {
    this.assignedEmployees = taskFusion.getProjectFacade().getProjectEmployees(projectNumber);
  }

  @Then("the employee list contains {int} items")
  public void theEmployeeListContainsItems(Integer int1) {
    assertTrue(this.assignedEmployees.size() == 2);
  }

    @Then("the user have {int} projects")
    public void the_user_have_projects(int i) {
        assertEquals(1, taskFusion.getProjectFacade().getUserProjects().size());
    }
}
