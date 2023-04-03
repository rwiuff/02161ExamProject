package taskfusion.persistency;
import java.util.Calendar;
import taskfusion.app.DateServer;



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
        
        projectRepository.create("Kravspecifikationer", date);
        projectRepository.create("Programdesign", date);
        // projectRepository.create("Implementering", date);
        // projectRepository.create("Test", date);
    }
            

}
