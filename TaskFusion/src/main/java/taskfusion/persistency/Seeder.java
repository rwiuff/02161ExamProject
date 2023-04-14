package taskfusion.persistency;
import java.util.Calendar;
import java.util.Map;

import taskfusion.app.DateServer;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;



public class Seeder {
    
    EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
    ProjectRepository projectRepository = ProjectRepository.getInstance();

    public Seeder() {

    }

    public void seedDemoData() {
        try {
            seedEmployees();
            seedProjects();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void seedEmployees() throws Exception {

        employeeRepository.create("Kasper", "Sylvest");
        employeeRepository.create("Rasmus", "Kronborg");
        employeeRepository.create("Mathies", "Christian");
        employeeRepository.create("Max-Emil", "Scotten");
        
    }

    public void seedProjects() throws Exception {
        DateServer dateServer = new DateServer();
        Calendar date = dateServer.getDate();

        Employee creator = EmployeeRepository.getInstance().findByInitials("kasy");
        
        projectRepository.create("Kravspecifikationer", date).assignEmployee(creator.getInitials(), creator);
        projectRepository.create("Programdesign", date).assignEmployee(creator.getInitials(), creator);
        projectRepository.create("Implementering", date).assignEmployee(creator.getInitials(), creator);
        projectRepository.create("Test", date).assignEmployee(creator.getInitials(), creator);

        //Assign users to projects
        for (Map.Entry<String, Project> projectEntry : ProjectRepository.getInstance().all().entrySet()) {

            Project project = projectEntry.getValue();
    
            project.assignEmployee("rakr", creator);
            project.assignEmployee("mach", creator);
            project.assignEmployee("masc", creator);
        }
    }
            

}
