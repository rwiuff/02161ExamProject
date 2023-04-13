package taskfusion.persistency;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;

/**
 * Singleton instance, getInstance and resetInstance provided by ChatGPT v4
 */
public class ProjectRepository {

    private Map<String, Project> projects = new HashMap<>();

    /**
     * SINGLETON related
     */
    // Declare a private static instance of the class
    private static ProjectRepository instance;

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

    /**
     * REPOSITORY related
     */

     public Map<String, Project> all() {
        return projects;
     }

    public void create(String projectTitle, Employee creator, Calendar date)
            throws OperationNotAllowedException, InvalidPropertyException, NotFoundException {

        if (projectTitle.length() < 2) {
            throw new InvalidPropertyException("En projekttitel mangler");
        }

        Project p = new Project(projectTitle, date);
        p.assignEmployee(creator.getInitials(), creator);
        String projectNumber = p.getProjectNumber();

        this.projects.put(projectNumber, p);
    }

    public Project findByProjectNumber(String projectNumber) throws NotFoundException {
        Project project = projects.get(projectNumber);
        if (project == null) {
            throw new NotFoundException("Projektet kunne ikke findes i samlingen af projekter");
        }
        return projects.get(projectNumber);

    }

    public Project findByTitle(String projectTitle) throws NotFoundException {
        Project returnProject = null;
        for (Entry<String, Project> entry: projects.entrySet()){
            if(entry.getValue().getProjectTitle().equals(projectTitle)){
                returnProject = entry.getValue();
            }
        }
        if(returnProject == null){
            throw new NotFoundException("Projektet kunne ikke findes i samlingen af projekter");
        }
        return returnProject;
    }

}
