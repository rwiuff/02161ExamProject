package taskfusion.persistency;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import taskfusion.domain.Project;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
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

        if (projectTitle.length() < 2) {
            throw new InvalidPropertyException("En projekttitel mangler");
        }

        Project p = new Project(projectTitle, date);
        String projectNumber = p.getProjectNumber();

        this.projects.put(projectNumber, p);
    }

    public Project getProject(String projectNumber) throws NotFoundException {
        Project project = projects.get(projectNumber);
        if (project == null) {
            throw new NotFoundException("Projektet kunne ikke findes i samlingen af projekter");
        }
        return projects.get(projectNumber);

        // for (Project p : this.projects) {
        // if (p.getProjectNumber() == projectNumber) {
        // return p;
        // }
        // }

        // return null;
    }

    public Project getByTitle(String projectTitle) throws NotFoundException {
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
