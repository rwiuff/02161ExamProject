package taskfusion.persistency;

import java.util.Calendar;
import java.util.Map;

import taskfusion.app.DateServer;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.ProjectActivity;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;

public class Seeder {

    EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
    ProjectRepository projectRepository = ProjectRepository.getInstance();
    DateServer dateServer = new DateServer();

    public Seeder() {

    }

    public void seedDemoData() throws Exception {
        seedEmployees();
        seedProjects();
    }

    public void seedEmployees() throws Exception {

        employeeRepository.create("Kasper", "Sylvest");
        employeeRepository.create("Rasmus", "Wiuff");
        employeeRepository.create("Mathies", "Christian");
        employeeRepository.create("Max-Emil", "Scotten");

    }

    public void seedProjects() throws Exception {
        Calendar date = dateServer.getDate();

        Employee creator = EmployeeRepository.getInstance().findByInitials("kasy");

        projectRepository.create("Kravspecifikationer", date).assignEmployee(creator.getInitials(), creator);
        projectRepository.create("Programdesign", date).assignEmployee(creator.getInitials(), creator);
        projectRepository.create("Implementering", date).assignEmployee(creator.getInitials(), creator);
        projectRepository.create("Test", date).assignEmployee(creator.getInitials(), creator);

        // Assign users to projects
        for (Map.Entry<String, Project> projectEntry : ProjectRepository.getInstance().all().entrySet()) {

            Project project = projectEntry.getValue();

            seedEmployeesToProject(project, creator);
            seedProjectActivitesToProject(project, creator);
        }

    }

    private void seedEmployeesToProject(Project project, Employee user)
            throws NotFoundException, OperationNotAllowedException {
        project.assignEmployee("rawi", user);
        project.assignEmployee("mach", user);
        project.assignEmployee("masc", user);
    }

    private void seedProjectActivitesToProject(Project project, Employee user)
            throws AlreadyExistsException, OperationNotAllowedException, InvalidPropertyException, NotFoundException {
        project.createProjectActivity("Brainstorm", "2301", "2302", user);
        project.createProjectActivity("Dokumentation", "2305", "2305", user);

        for (ProjectActivity activity : project.getActivities()) {
            seedWorktimeRegistrationsForProjectActivity(activity);
            activity.setTimeBudget(30);
        }
    }

    private void seedWorktimeRegistrationsForProjectActivity(ProjectActivity activity) throws NotFoundException, OperationNotAllowedException {

        activity.registerWorkTime(employeeRepository.findByInitials("rawi"), dateServer.getDate(), 1);
        activity.registerWorkTime(employeeRepository.findByInitials("rawi"), dateServer.getDate(), 5.5);

        activity.registerWorkTime(employeeRepository.findByInitials("mach"), dateServer.getDate(), 1);
        activity.registerWorkTime(employeeRepository.findByInitials("mach"), dateServer.getDate(), 5.5);

        activity.registerWorkTime(employeeRepository.findByInitials("kasy"), dateServer.getDate(), 1);
        activity.registerWorkTime(employeeRepository.findByInitials("kasy"), dateServer.getDate(), 5.5);

        activity.registerWorkTime(employeeRepository.findByInitials("masc"), dateServer.getDate(), 1);
        activity.registerWorkTime(employeeRepository.findByInitials("masc"), dateServer.getDate(), 5.5);
    }

}
