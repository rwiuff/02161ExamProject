package taskfusion.persistency;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.cucumber.java.it.Date;
import taskfusion.app.DateServer;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;

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

        employeeRepository.registerEmployee("Kasper", "Sylvest");
        employeeRepository.registerEmployee("Rasmus", "Kronborg");
        employeeRepository.registerEmployee("Mathies", "Christian");
        employeeRepository.registerEmployee("Max-Emil", "Scotten");
        
    }

    public void seedProjects() throws Exception {
        DateServer dateServer = new DateServer();
        Calendar date = dateServer.getDate();
        
        projectRepository.createProject("Kravspecifikationer", date);
        projectRepository.createProject("Programdesign", date);
        //projectRepository.createProject("Implementering", date);
        // projectRepository.createProject("Test", date);
    }
            

}
