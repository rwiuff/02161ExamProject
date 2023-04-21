package taskfusion.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.helpers.MockDateHolder;
import taskfusion.viewModels.ReportViewModel;

public class ReportSteps {
    private TaskFusion taskFusion;
    private ErrorMessageHolder errorMessageHolder;
    private MockDateHolder mockDateHolder;

    public ReportSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion,
            MockDateHolder mockDateHolder) {
        this.taskFusion = taskFusion;
        this.errorMessageHolder = errorMessageHolder;
        this.mockDateHolder = mockDateHolder;
    }

    @When("the user generates a report for project {string}")
    public void theUserGeneratesAReportForProject(String projectNumber) {
        try {
            taskFusion.getProjectFacade().generateProjectRaport(projectNumber);
        } catch (Exception e) {
            this.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the number of reports for project {string} is {int}")
    public void the_number_of_reports_for_project_is(String projectNumber, int inumberOfReports) {
        try {
            Map<String, ReportViewModel> reports = taskFusion.getProjectFacade().findProjectByProjectNumber(projectNumber).reports;
            assertEquals(inumberOfReports, reports.size());
        } catch (Exception e) {
            this.errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Given("the date is {int}.{int}.{int}")
    public void the_date_is(int day, int month, int year) {
        Calendar calendar = new GregorianCalendar(day, month, year);
        mockDateHolder.setDate(calendar);
    }
}
