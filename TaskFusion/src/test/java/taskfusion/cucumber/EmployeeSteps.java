package taskfusion.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;

public class EmployeeSteps {
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();
    private TaskFusion taskFusion = new TaskFusion();

    private Employee employee;

    public EmployeeSteps(ErrorMessageHolder errorMessageHolder, TaskFusion taskFusion) {
      this.errorMessageHolder = errorMessageHolder;
      this.taskFusion = taskFusion;
    }

    @Then("an employee with first name {string}, last name {string} and initials {string} exists in the application")
    public void an_employee_with_first_name_last_name_and_initials_exists_in_the_application(String firstName,
            String lastName, String initials) {
        Employee employee = taskFusion.findEmployee(initials);

        assertEquals(employee.getFirstName(), firstName);
        assertEquals(employee.getLastName(), lastName);
        assertEquals(employee.getInitials(), initials);
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
        assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
    }

    @Then("the application has a registered employee with first name {string}, last name {string}")
    public void the_application_has_a_registered_employee_with_first_name_last_name(String firstName, String lastName) {
        try {
            taskFusion.registerEmployee(firstName, lastName);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the user registers an employee with first name {string}, last name {string}")
    public void the_user_registers_an_employee_with_first_name_last_name(String firstName, String lastName) {
        //this.employee = new Employee(firstName, lastName);
        try {
            taskFusion.registerEmployee(firstName, lastName);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @Then("the user with initials {string} is logged in as an employee")
    public void the_user_with_initials_is_logged_in_as_an_employee(String initials) {
        assertTrue(taskFusion.isLoggedIn());
        Employee loggedInUser = taskFusion.getLoggedInUser();
        assertEquals(initials, loggedInUser.getInitials());
    }

    @When("the user logs in using initials {string}")
    public void the_user_logs_in_using_initials(String initials) {
        try {
            taskFusion.login(initials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("none is logged in")
    public void none_is_logged_in() {
        assertFalse(taskFusion.isLoggedIn());
    }

    @When("the user logs out")
    public void the_user_logs_out() {
        taskFusion.logout();
    }
        
}