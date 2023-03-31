package taskfusion.persistency;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import taskfusion.domain.Project;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.OperationNotAllowedException;

public class ProjectRepository {

    // Declare a private static instance of the class
    private static ProjectRepository instance;

    public Map<String, Project> projects = new HashMap<>();

    // Private constructor to prevent instantiation from other classes
    private ProjectRepository() {
       
    }

    // Public method to get the Singleton instance
    public static ProjectRepository getInstance() {
        if (instance == null) {
            instance = new ProjectRepository();
        }
        return instance;

    }
    
    public static void resetInstance() {
        instance = null;
    }

    public void createProject(String projectTitle, Calendar date)
            throws OperationNotAllowedException, InvalidPropertyException {

        if(projectTitle == "") {
            throw new InvalidPropertyException("En projekttitel mangler");
        }

        Project p = new Project(projectTitle, date);
        String projectNumber = p.getProjectNumber();
        System.out.println("Creating project with year " + date.get(Calendar.YEAR));
        System.out.println("Creating project with projectnumber " + projectNumber);
        this.projects.put(projectNumber, p);
    }

    public Project getProject(String projectNumber) {
        return projects.get(projectNumber);
        // for (Project p : this.projects) {
        // if (p.getProjectNumber() == projectNumber) {
        // return p;
        // }
        // }

        // return null;
    }

}
