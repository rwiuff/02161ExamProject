package taskfusion.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;

public class TaskFusion {
    private List<Project> projects = new ArrayList<Project>();
    private DateServer dateServer = new DateServer();
    private Map<String, Employee> employees = new HashMap<>();
    private Employee loggedInUser;

    public static void main(String[] args) {

    }

    public void registerEmployee(String firstName, String lastName) throws InvalidPropertyException, AlreadyExistsException {
        Employee employee = new Employee(firstName, lastName);
        String initials = employee.getInitials();

        if(findEmployee(initials) != null) {
            throw new AlreadyExistsException("Medarbejder ekisisterer allerede");
        }
        
        employees.put(initials, employee);
    }

    public Employee findEmployee(String initials) {
        String formattedInitials = initials.toLowerCase();
        return employees.get(formattedInitials);
    }

    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    public void login(String initials) throws NotFoundException {
        loggedInUser = findEmployee(initials);

        if(loggedInUser == null) {
            throw new NotFoundException("Ukendt medarbejder");
        }
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void createProject(String projectTitle) throws OperationNotAllowedException {
      if (projectTitle != "") {
        if (!isLoggedIn()) {
          throw new OperationNotAllowedException("Kun medarbejdere kan oprette et projekt");
        } else {
          this.projects.add(new Project(projectTitle, this.dateServer.getDate().getWeekYear(), "001"));
        }
      } else {
        throw new OperationNotAllowedException("En projekttitel mangler");
      }
    }

    public void setDateServer(DateServer dateServer) {
      this.dateServer = dateServer;
    }

    public Project findProject(Integer projectNumber) {
      for (Project p : this.projects) {
        if (p.getProjectNumber() == projectNumber) {
          return p;
        }
      }

      return null;
    }
}