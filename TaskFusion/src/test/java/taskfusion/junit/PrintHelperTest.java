package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.WorktimeRegistration;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.PrintHelper;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class PrintHelperTest {

    @BeforeEach
    public void resetSingletons() {
        SingletonHelpers.resetSingletons();
    }

    @Test
    public void testPrintEmployees() throws InvalidPropertyException, ExhaustedOptionsException {
        EmployeeRepository.getInstance().create("Michael", "Laudrup");
        PrintHelper.printEmployees(EmployeeRepository.getInstance().all());
    }

    @Test
    public void testPrintProjects() throws OperationNotAllowedException, InvalidPropertyException, NotFoundException,
            ExhaustedOptionsException {
        EmployeeRepository.getInstance().create("Michael", "Laudrup");
        // Employee creator = EmployeeRepository.getInstance().findByInitials("mila");

        ProjectRepository.getInstance().create("Demo 1", new DateServer().getDate());
        PrintHelper.printProjects(ProjectRepository.getInstance().all());

        ProjectRepository.getInstance().create("Demo 1", new DateServer().getDate());

    }

    @Test
    public void testPrintWorktimeRegistrations() {
        Calendar date = new DateServer().getDate();

        List<WorktimeRegistration> models = new ArrayList<>();

        models.add(new WorktimeRegistration("mila", date, 10.0));
        models.add(new WorktimeRegistration("mila", date, 3));

        PrintHelper.printWorktimeRegistrations(models);
    }

    @Test
    public void testConstructPrintHelper() {
        PrintHelper printHelper = new PrintHelper();
        assertTrue(printHelper != null);
    }

}
