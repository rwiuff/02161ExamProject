package taskfusion.persistency;
import java.util.Calendar;
import taskfusion.app.DateServer;
import taskfusion.domain.Employee;



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
        
        projectRepository.create("Kravspecifikationer",creator , date);
        projectRepository.create("Programdesign", creator, date);
        projectRepository.create("Implementering", creator, date);
        projectRepository.create("Test", creator, date);
    }
            

}
