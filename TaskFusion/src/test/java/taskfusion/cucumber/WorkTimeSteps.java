package taskfusion.cucumber;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;

public class WorkTimeSteps {
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    private TaskFusion taskFusion = new TaskFusion();

    public WorkTimeSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
        this.errorMessageHolder = errorMessageHolder;
        this.taskFusion = taskFusion;
    }

    @When("the user registers a work time of {double} hours to the project activity with title {string} in the project with project number {string}")
    public void theUserRegistersAWorkTimeOfHoursToTheProjectActivityWithTitleInTheProjectWithProjectNumber(
            double worktTime,
            String activityTitle, String projectNumber) {
        try {
            this.taskFusion.getProjectFacade().registerWorkTime(projectNumber, activityTitle, worktTime);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user has {double} hours of registered work time on the project activity with title {string} and project number {string}")
    public void theUserHasHoursOfRegisteredWorkTimeOnTheProjectActivityWithTitleAndProjectNumber(double workTime,
            String activityTitle, String projectNumber) {
        try {
            assertEquals(workTime, this.taskFusion.getProjectFacade().getTotalWorkTimeForEmployee(projectNumber, activityTitle, workTime), .1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}