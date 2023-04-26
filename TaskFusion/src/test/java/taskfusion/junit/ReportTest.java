package taskfusion.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.ReportViewModel;

public class ReportTest {
    private TaskFusion taskFusion;
    private String[][] employeeStrings = { { "Rasmus", "Wiuff" }, { "Max-Emil", "Scotten" },
            { "Mathies Christian Ellehauge", "Henriksen" }, { "Kasper", "Sylvest" } };
    private Project project;
    private String projectNumber;
    private Map<String, Employee> employees = new HashMap<>();

    @TempDir
    File tmp;

    @BeforeEach
    public void resetSingletons() throws InvalidPropertyException, ExhaustedOptionsException,
            OperationNotAllowedException, NotFoundException, AlreadyExistsException {
        SingletonHelpers.resetSingletons();
        testSetup();
    }

    private void testSetup() throws InvalidPropertyException, ExhaustedOptionsException, OperationNotAllowedException,
            NotFoundException, AlreadyExistsException {
        this.taskFusion = new TaskFusion();
        for (int i = 0; i < employeeStrings.length; i++) {
            taskFusion.getEmployeeFacade().registerEmployee(employeeStrings[i][0], employeeStrings[i][1]);
        }
        taskFusion.login("rawi");
        taskFusion.getProjectFacade().createProject("TaskFusion");
        this.project = taskFusion.getProjectFacade().projectRepo.all().values().stream()
                .filter(p -> p.getProjectTitle().equals("TaskFusion")).findFirst().get();
        this.projectNumber = project.getProjectNumber();
        this.employees = taskFusion.getEmployeeFacade().employeeRepo.all();
        taskFusion.getProjectFacade().assignCustomerToProject(projectNumber, "DTU");
        taskFusion.getProjectFacade().createProjectActivity(projectNumber, "AcceptanceTests", "2306", "2316");
        taskFusion.getProjectFacade().setTimeBudget(projectNumber, "AcceptanceTests", 50);
        taskFusion.getProjectFacade().createProjectActivity(projectNumber, "UnitTests", "2310", "2316");
        taskFusion.getProjectFacade().setTimeBudget(projectNumber, "UnitTests", 80);
        for (String employee : employees.keySet()) {
            taskFusion.getProjectFacade().assignEmployeeToProject(projectNumber,
                    employees.get(employee).getInitials());
            taskFusion.login(employee);
            taskFusion.getProjectFacade().registerWorkTime(projectNumber, "AcceptanceTests", 3.5);
            taskFusion.getProjectFacade().registerWorkTime(projectNumber, "AcceptanceTests", 7.5);
            taskFusion.getProjectFacade().registerWorkTime(projectNumber, "UnitTests", 2);
            taskFusion.getProjectFacade().registerWorkTime(projectNumber, "UnitTests", 11);
        }
    }

    @Test
    public void testReportGeneration() throws NotFoundException, AlreadyExistsException, OperationNotAllowedException,
            InvalidPropertyException, ExhaustedOptionsException {
        taskFusion.login("rawi");
        taskFusion.getProjectFacade().takeProjectLeaderRole(projectNumber);
        assertNotNull(taskFusion.getProjectFacade().generateProjectRaport(projectNumber));
    }

    @Test
    public void testReportPDFGeneration() throws NotFoundException, AlreadyExistsException,
            OperationNotAllowedException, InvalidPropertyException, ExhaustedOptionsException, IOException,
            URISyntaxException {
        taskFusion.login("rawi");
        taskFusion.getProjectFacade().takeProjectLeaderRole(projectNumber);
        ReportViewModel reportViewModel = taskFusion.getProjectFacade().generateProjectRaport(projectNumber);
        taskFusion.getProjectFacade().saveReport(projectNumber, reportViewModel.reportDate, tmp.getAbsolutePath());
        File pdf = Paths.get(tmp.getAbsolutePath() + projectNumber + "/" + projectNumber + " "
                + project.getProjectTitle() + " " + reportViewModel.reportDate + ".pdf").toFile();
        assertTrue(pdf.exists());
    }
}
