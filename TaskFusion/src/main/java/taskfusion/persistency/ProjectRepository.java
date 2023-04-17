package taskfusion.persistency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.WorktimeRegistration;
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

    public Project create(String projectTitle, Calendar date)
            throws OperationNotAllowedException, InvalidPropertyException, NotFoundException {

        if (projectTitle.length() < 2) {
            throw new InvalidPropertyException("En projekttitel mangler");
        }

        Project p = new Project(projectTitle, date);
        String projectNumber = p.getProjectNumber();

        this.projects.put(projectNumber, p);
        return p;
    }

    public Project findByProjectNumber(String projectNumber) throws NotFoundException {
        Project project = projects.get(projectNumber);
        if (project == null) {
            throw new NotFoundException("Projektet kunne ikke findes i samlingen af projekter");
        }
        return projects.get(projectNumber);

    }

    private List<WorktimeRegistration> allWorktimeRegistrations() {

        List<WorktimeRegistration> list = new ArrayList<>();

        for (Entry<String, Project> entry: projects.entrySet()){

            Project project = entry.getValue();

            list.addAll(project.getWorktimeRegistrations());
        }
        return list;
    }

    public WorktimeRegistration findWorktimeRegistrationById(int id) throws NotFoundException {

        List<WorktimeRegistration> list = allWorktimeRegistrations();

        for(WorktimeRegistration worktimeRegistration : list) {
            if(worktimeRegistration.getId().equals(id)) {
                return worktimeRegistration;
            }
        }

        throw new NotFoundException("Ukendt tidsregistrering");

    }

    public Integer generateWorktimeRegistrationId() {
        Integer lastId = 0;

        List<WorktimeRegistration> list = allWorktimeRegistrations();

        for(WorktimeRegistration worktimeRegistration : list) {
            Integer id = worktimeRegistration.getId();
            if(id > lastId) {
                lastId = id;
            }
        }

        return lastId + 1;
    }

    public List<Employee> getListOfEmployees(String projectNumber) {
      return this.projects.get(projectNumber).getListOfAssignedEmployees();
    }

}
