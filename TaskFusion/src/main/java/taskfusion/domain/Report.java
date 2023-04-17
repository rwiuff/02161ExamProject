package taskfusion.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import taskfusion.viewModels.ReportViewModel;

public class Report implements ConvertibleToViewModelInterface {
    private String title;
    private String projectNumber;
    private Employee projectLeader;
    private Map<String, Employee> employees = new HashMap<>();
    private String customer;
    private int startWeek;
    private int endWeeek;
    private List<ProjectActivity> activities = new ArrayList<>();
    private Calendar reportDate;

    public Report(Project project, Calendar date) {
        reportDate = date;
        this.title = project.getProjectTitle();
        this.projectNumber = project.getProjectNumber();
        this.projectLeader = project.getProjectLeader();
        this.employees = project.getAssignedEmployees();
        this.customer = project.getCustomer();
        this.startWeek = project.getStartWeek();
        this.endWeeek = project.getEndWeek();
        this.activities = project.getActivities();
    }

    @Override
    public ReportViewModel toViewModel() {
        return new ReportViewModel(this);
    }

}
