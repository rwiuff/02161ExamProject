package taskfusion.cucumber;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;

public class ProjectActivitySteps {
  private ErrorMessageHolder errorMessageHolder;
  private TaskFusion taskFusion;
  private List<WorktimeRegistration> worktimeRegistrations;
  private Double timeSum;

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

  @When("the user requests a list of own worktime registrations for the activity titled {string} in the project with project number {string}")
    public void the_user_requests_a_list_of_own_worktime_registrations_for_the_activity_titled_in_the_project_with_project_number(String activityTitle, String projectNumber) throws NotFoundException {
      this.worktimeRegistrations = this.taskFusion.getUserWorktimeRegistrationsForProjectActivity(activityTitle, projectNumber);  
      try {
          this.worktimeRegistrations = this.taskFusion.getUserWorktimeRegistrationsForProjectActivity(activityTitle, projectNumber);
          
        } catch (Exception e) {
          this.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
  
  
    @Then("the worktime registration list contains {int} items")
    public void the_worktime_registration_list_contains_items(int i) {
        assertTrue(this.worktimeRegistrations.size() == i);
    }

    @Then("{double} hours is returned")
    public void hours_is_returned(Double i) {
        assertEquals(i, timeSum);
    }

    @When("the user requests a sum of own worktime registrations for the activity titled {string} in the project with project number {string}")
    public void the_user_requests_a_sum_of_own_worktime_registrations_for_the_activity_titled_in_the_project_with_project_number(String activityTitle, String projectNumber) {
      try {
        this.timeSum = this.taskFusion.getUserWorktimeForProjectActivity(activityTitle, projectNumber);
        
      } catch (Exception e) {
        this.errorMessageHolder.setErrorMessage(e.getMessage());
      }
    }

    @Then("worktime registration with id {int} has a worktime of {int} hours")
    public void worktime_registration_with_id_has_a_worktime_of_hours(int id, int hours) {
        assertEquals(hours, taskFusion.projectRepo.findWorktimeRegistrationById(id).getTime());
    }

    @Then("the user edits the worktime registration with id {int} to {double} hours")
    public void the_user_edits_the_worktime_registration_with_id_to_hours(int id, double hours) {
        try {
          taskFusion.editWorktimeRegistration(id,hours);
        } catch (Exception e) {
          this.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
