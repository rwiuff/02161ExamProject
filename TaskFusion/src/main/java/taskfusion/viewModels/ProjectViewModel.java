package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import taskfusion.domain.Project;
import taskfusion.domain.Report;

public class ProjectViewModel extends ViewModel {
    public String projectNumber;
    public String projectTitle;
    public String customer;
    //public int startWeek;
    //public int endWeek;
    public String projectLeaderFullName;
    public int assignedEmployeesAmount;
    public List<ProjectActivityViewModel> projectActivities;
    public Map<String, ReportViewModel> reports = new HashMap<>();

    public ProjectViewModel(Project project) {
        this.projectNumber = project.getProjectNumber();
        this.projectTitle = project.getProjectTitle();
        this.customer = project.getCustomer();
        //this.startWeek = project.getStartWeek();
        //this.endWeek = project.getEndWeek();
        this.projectActivities = ProjectActivityViewModel.listFromModels(project.getActivities());

        if (project.getProjectLeader() != null) {
            this.projectLeaderFullName = project.getProjectLeader().getFullName();
        }

        this.assignedEmployeesAmount = project.getAssignedEmployees().size();
        importReports(project);
    }

    private void importReports(Project project) {
        Map<String, Report> importedReports = project.getReports();
        Set<String> dates = project.getReports().keySet();
        for (String date : dates) {
            this.reports.put(date, importedReports.get(date).toViewModel());
        }
    }

    public static List<ProjectViewModel> listFromModels(List<Project> modelList) {

        List<ProjectViewModel> list = new ArrayList<ProjectViewModel>();

        for (Project item : modelList) {
            list.add(item.toViewModel());
        }

        return list;
    }
}
