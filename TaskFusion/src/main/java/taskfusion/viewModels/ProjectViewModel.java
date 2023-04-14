package taskfusion.viewModels;
import java.util.ArrayList;
import java.util.List;

import taskfusion.domain.Project;

public class ProjectViewModel extends ViewModel {
    public String projectNumber;
    public String projectTitle;
    public String customer;
    public int startWeek;
    public int endWeek;
    public String projectLeaderFullName;
    public int assignedEmployeesAmount;

    public ProjectViewModel(Project project) {
        this.projectNumber = project.getProjectNumber();
        this.projectTitle = project.getProjectTitle();
        this.customer = project.getCustomer();
        this.startWeek = project.getStartWeek();
        this.endWeek = project.getEndWeek();
        
        if(project.getProjectLeader() != null) {
            this.projectLeaderFullName = project.getProjectLeader().getFullName();
        }

        this.assignedEmployeesAmount = project.getAssignedEmployees().size();
    }

    public static List<ProjectViewModel> listFromModels(List<Project> modelList) {
        
        List<ProjectViewModel> list = new ArrayList<ProjectViewModel>();

        for(Project item : modelList) {
            list.add(item.toViewModel());
        }

        return list;
    }
}
