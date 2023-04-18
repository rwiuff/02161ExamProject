package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import taskfusion.domain.ProjectActivity;
import taskfusion.domain.Report;

public class ReportViewModel extends ViewModel {

    public String title;
    public String projectNumber;
    public String projectLeader;
    public Map<String, EmployeeViewModel> employees = new HashMap<>();
    public String customer;
    public int startWeek;
    public int endWeeek;
    public List<ProjectActivityViewModel> activities = new ArrayList<>();
    public String reportDate;

    public ReportViewModel(Report report) {
        this.title = report.getTitle();
        this.projectNumber = report.getProjectNumber();
        this.projectLeader = report.getProjectLeader().getInitials();
        Set<String> employeeSet = report.getEmployees().keySet();
        for (String initials : employeeSet) {
            this.employees.put(initials, report.getEmployee(initials).toViewModel());
        }
        this.customer = report.getCustomer();
        this.startWeek = report.getStartWeek();
        this.endWeeek = report.getEndWeeek();
        List<ProjectActivity> activityList = report.getActivities();
        for (ProjectActivity activity : activityList) {
            activities.add(activity.toViewModel());
        }
        this.reportDate = report.getDateAsString();
    }

}
