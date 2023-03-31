package taskfusion.persistency;

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
        // Your initialization code here, if any
    }

    // Public method to get the Singleton instance
    public static ProjectRepository getInstance() {
        if (instance == null) {
            instance = new ProjectRepository();
        }
        return instance;

    }

    public void createProject(String projectTitle, int year)
            throws OperationNotAllowedException, InvalidPropertyException {

        if(projectTitle == "") {
            throw new InvalidPropertyException("En projekttitel mangler");
        }

        Project p = new Project(projectTitle, year);
        String projectNumber = "" + p.getProjectNumber();
        this.projects.put(projectNumber, p);
    }

    public Project findProject(int projectNumber) {
        return projects.get(projectNumber + "");
        // for (Project p : this.projects) {
        // if (p.getProjectNumber() == projectNumber) {
        // return p;
        // }
        // }

        // return null;
    }

}
