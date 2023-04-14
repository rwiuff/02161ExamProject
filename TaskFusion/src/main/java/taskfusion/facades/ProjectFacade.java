package taskfusion.facades;

import taskfusion.app.TaskFusion;
import taskfusion.domain.Project;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.persistency.ProjectRepository;

public class ProjectFacade {
    private TaskFusion taskFusion;
    public ProjectRepository projectRepo = ProjectRepository.getInstance();

    public ProjectFacade(TaskFusion taskFusion) {
        this.taskFusion = taskFusion;
    }

    public void createProject(String projectTitle)
            throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException {
        if (!taskFusion.isLoggedIn()) {
            throw new OperationNotAllowedException("Kun medarbejdere kan oprette et projekt");
        } else {
            projectRepo.create(projectTitle, taskFusion.getLoggedInUser(), taskFusion.getDate());
        }
    }

    public void assignCustomerToProject(String projectNumber, String customer) throws NotFoundException {
        projectRepo.findByProjectNumber(projectNumber).setCustomer(customer);
    }

    public void assignEmployeeToProject(String projectNumber, String initials)
            throws NotFoundException, OperationNotAllowedException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        project.assignEmployee(initials, taskFusion.getLoggedInUser());
    }

    public Project findProjectByProjectNumber(String projectNumber) throws NotFoundException {
        Project project = projectRepo.findByProjectNumber(projectNumber);
        return project;
    }
    
}
