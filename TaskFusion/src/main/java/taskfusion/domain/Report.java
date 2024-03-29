package taskfusion.domain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import taskfusion.helpers.DateHelper;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.ReportViewModel;

public class Report implements ConvertibleToViewModelInterface {
    private String title;
    private String projectNumber;
    private Employee projectLeader;
    private Map<String, Employee> employees = new HashMap<>();
    private String customer;
    private List<ProjectActivity> activities = new ArrayList<>();
    private Calendar reportDate;

    public Report(Project project, Calendar date, Employee employee) throws NotFoundException, OperationNotAllowedException {
        this.reportDate = date;
        this.title = project.getProjectTitle();
        this.projectNumber = project.getProjectNumber();
        this.projectLeader = project.getProjectLeader();
        this.employees = project.getAssignedEmployees();
        this.customer = project.getCustomer();
        this.activities = project.getActivities();
        if(project.getProjectLeader() == null){
            throw new NotFoundException("Projektet mangler en projektleder for at genererer rapporter");
        }
        if (!employee.getInitials().equals(projectLeader.getInitials())) {
            throw new OperationNotAllowedException("Kun projektlederen kan generere rapporter");
        }
    }

    @Override
    public ReportViewModel toViewModel() {
        return new ReportViewModel(this);
    }

    public String getTitle() {
        return title;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(String initials) {
        return employees.get(initials);
    }

    public String getCustomer() {
        return customer;
    }

    public List<ProjectActivity> getActivities() {
        return activities;
    }

    public Calendar getReportDate() {
        return reportDate;
    }

    public String getDateAsString() {
        return DateHelper.getDateAsString(reportDate);
    }

    public void saveReport(String saveDirectory) throws IOException, URISyntaxException {
        new ReportPDFGenerator(this).save(saveDirectory);
    }

}
