package taskfusion.cucumber;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import taskfusion.Employee;
import taskfusion.TaskFusion;

public class EmployeeSteps {
    
    @When("the user registers an employee with first first name {string}, last name {string}")
    public void the_user_registers_an_employee_with_first_first_name_last_name_and_initials(String firstName, String lastName) {
        TaskFusion.createEmployee(firstName,lastName);
    }

    @Then("an employee with first name {string}, last name {string} and initials {string} exists in the application")
    public void an_employee_with_first_name_last_name_and_initials_exists_in_the_application(String firstName, String lastName, String initials) {
        Employee employee = TaskFusion.findEmployee(initials);

        assertEquals(employee.getFirstName(),firstName);
        assertEquals(employee.getFirstName(),lastName);
        assertEquals(employee.getInitials(),initials);

    }
}
