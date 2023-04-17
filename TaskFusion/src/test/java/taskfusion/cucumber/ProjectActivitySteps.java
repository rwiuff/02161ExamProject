package taskfusion.cucumber;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class ProjectActivitySteps {
  private ErrorMessageHolder errorMessageHolder;
  private TaskFusion taskFusion;
  private List<WorktimeRegistrationViewModel> worktimeRegistrations;
  private Double timeSum;
  private Double remainingWorkTime;

  public ProjectActivitySteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
    this.errorMessageHolder = errorMessageHolder;
    this.taskFusion = taskFusion;
  }

  @Given("the user assigns the project activity {string} to project {string} with startWeek {int} and endWeek {int}")
  public void theUserAssignsTheProjectActivityToProjectWithStartWeekAndEndWeek(String title, String projectNumber,
      Integer startWeek, Integer endWeek) {
    try {
      taskFusion.getProjectFacade().createProjectActivity(projectNumber, title, startWeek, endWeek);
    } catch (Exception e) {
      errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project with the project number {string} has a project activity titled {string}")
  public void theProjectWithTheProjectNumberHasAProjectActivityTitled(String projectNumber, String title) {
    try {
      assertNotNull(ProjectRepository.getInstance().findByProjectNumber(projectNumber).findProjectActivity(title));
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user sets the time budget to {int} hours on the project activity with the title {string} and project number {string}")
  public void theUserSetsTheTimeBudgetToHoursOnTheProjectActivityWithTheTitleAndProjectNumber(Integer timeBudget,
      String projectActivityTitle, String projectNumber) {
    try {
      taskFusion.getProjectFacade().setTimeBudget(projectNumber, projectActivityTitle, timeBudget);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with the title {string} and project number {string} has a time budget of {int} hours")
  public void theProjectActivityWithTheTitleAndProjectNumberHasATimeBudgetOfHours(String title, String projectNumber,
      Integer hours) {
    try {
      assertEquals(hours, ProjectRepository.getInstance().findByProjectNumber(projectNumber).findProjectActivity(title)
          .getTimeBudget());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with title {string} and project number {string} has end week {int}")
  public void theProjectActivityWithTitleAndProjectNumberHasEndWeek(String title, String projectNumber,
      Integer endWeek) {
    try {
      assertEquals(endWeek,
          ProjectRepository.getInstance().findByProjectNumber(projectNumber).findProjectActivity(title).getEndWeek());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project activity with the title {string} and project number {string} has start week {int}")
  public void theProjectActivityWithTheTitleAndProjectNumberHasStartWeek(String string, String string2, Integer int1) {
    try {
      assertEquals(int1,
          ProjectRepository.getInstance().findByProjectNumber(string2).findProjectActivity(string).getStartWeek());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @When("the user requests a list of own worktime registrations for the activity titled {string} in the project with project number {string}")
  public void the_user_requests_a_list_of_own_worktime_registrations_for_the_activity_titled_in_the_project_with_project_number(
      String activityTitle, String projectNumber) {

    try {
      this.worktimeRegistrations = taskFusion.getProjectFacade()
          .getUserWorktimeRegistrationsForProjectActivity(activityTitle, projectNumber);
      // PrintHelper.printWorktimeRegistrations(this.worktimeRegistrations);
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
  public void the_user_requests_a_sum_of_own_worktime_registrations_for_the_activity_titled_in_the_project_with_project_number(
      String activityTitle, String projectNumber) {
    try {
      this.timeSum = taskFusion.getProjectFacade().getUserWorktimeForProjectActivity(activityTitle, projectNumber);

    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("worktime registration with id {int} has a worktime of {int} hours")
  public void worktime_registration_with_id_has_a_worktime_of_hours(int id, int hours) {
    try {
      assertEquals(hours, ProjectRepository.getInstance().findWorktimeRegistrationById(id).getTime());
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }
  }

  @Then("the user edits the worktime registration with id {int} to {double} hours")
  public void the_user_edits_the_worktime_registration_with_id_to_hours(int id, double hours) {
    try {
      taskFusion.getProjectFacade().editWorktimeRegistration(id, hours);
    } catch (Exception e) {
      this.errorMessageHolder.setErrorMessage(e.getMessage());
    }

  }

  @When("the user requests the remaining time on {string} on project {string}")
    public void the_user_requests_the_remaining_time_on_on_project(String activityTitle, String projectNumber) {
      try{
        this.remainingWorkTime = taskFusion.getProjectFacade().getRemainingWorktimeForActivity(projectNumber, activityTitle);
      } catch (Exception e) {
        this.errorMessageHolder.setErrorMessage(e.getMessage());
      }
    }

    @Then("the activity returns {double} hours")
    public void the_activity_returns_hours(double remainingTimeOnActivity) {
        assertEquals(this.remainingWorkTime, remainingTimeOnActivity, .1);
    }
}
