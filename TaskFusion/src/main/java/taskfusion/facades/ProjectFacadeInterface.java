package taskfusion.facades;

import java.util.List;
import taskfusion.domain.Project;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.EmployeeViewModel;
import taskfusion.viewModels.ProjectViewModel;

public interface ProjectFacadeInterface {
    
    public Project createProject(String title) throws OperationNotAllowedException, InvalidPropertyException, NotFoundException, AlreadyExistsException;
    public void assignCustomerToProject(String projectNumber, String customerName) throws NotFoundException;
    public void assignEmployeeToProject(String projectNumber, String initials) throws NotFoundException, OperationNotAllowedException;
    public void takeProjectLeaderRole(String projectNumber) throws AlreadyExistsException, NotFoundException;
    public ProjectViewModel findProjectByProjectNumber(String projectNumber) throws NotFoundException;
    public List<ProjectViewModel> getUserProjects() throws NotFoundException;
    public List<EmployeeViewModel> getProjectEmployees(String projectNumber);

}
