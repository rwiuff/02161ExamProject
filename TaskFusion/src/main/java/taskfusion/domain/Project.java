package taskfusion.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.DateHelper;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.ProjectViewModel;

public class Project implements ConvertibleToViewModelInterface {
  private String projectNumber;
  private String projectTitle;
  private String customer;
  private Employee projectLeader;
  private int startWeek;
  private int endWeek;
  private Map<String, Employee> assignedEmployees = new HashMap<>();
  private List<ProjectActivity> activities = new ArrayList<ProjectActivity>();
  private Map<String, Report> reports = new HashMap<>();

  public Project(String projectTitle, Calendar date) {
    this.projectTitle = projectTitle;
    this.projectNumber = generateProjectNumber(date);
  }

  public ProjectViewModel toViewModel() {
    return new ProjectViewModel(this);
  }

  public int getStartWeek() {
    return this.startWeek;
  }

  public void setStartWeek(int startWeek) {
    // Husk at kaste InvalidPropertyException, når
    // du laver activities.
    this.startWeek = startWeek;
  }

  public int getEndWeek() {
    return this.endWeek;
  }

  public void setEndWeek(int endWeek) {
    // Husk at kaste InvalidPropertyException, når
    // du laver activities.
    this.endWeek = endWeek;
  }

  public boolean isInternal() {
    return customer == null;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getCustomer() {
    return this.customer;
  }

  public String getProjectTitle() {
    return this.projectTitle;
  }

  public String getProjectNumber() {
    return this.projectNumber;
  }

  /**
   * Generate a unique 5 digit project number as a string. Always leading with
   * two-digit-year
   * followed by incemental number.
   * 
   * For loop to identify highest last number, by ChatGPT v3.5
   */
  public static String generateProjectNumber(Calendar date) {

    int year = DateHelper.twoDigitYearFromDate(date);
    int lastNum = 0;

    // Find the highest incremental number for the current year
    for (String projectNumber : ProjectRepository.getInstance().all().keySet()) {
      if (projectNumber.startsWith(String.format("%02d", year))) {
        int num = Integer.parseInt(projectNumber.substring(2));
        if (num > lastNum) {
          lastNum = num;
        }
      }
    }

    int nextProjectNumber = (year * 1000) + lastNum + 1;

    if (nextProjectNumber < 10000) {
      return "0" + nextProjectNumber;
    }

    return "" + nextProjectNumber;

  }

  public void setProjectLeader(Employee employee) throws AlreadyExistsException {
    if (this.projectLeader != null) {
      throw new AlreadyExistsException("Der kan kun være en projektleder");
    }
    this.projectLeader = employee;
  }

  public Employee getProjectLeader() {
    return this.projectLeader;
  }

  public Employee getAssignedEmployee(String initials) {
    return assignedEmployees.get(initials);
  }

  public Map<String, Employee> getAssignedEmployees() {
    return assignedEmployees;
  }

  public void assignEmployee(String employeeInitials, Employee loggedInUser)
      throws NotFoundException, OperationNotAllowedException {

    Employee employee = EmployeeRepository.getInstance().findByInitials(employeeInitials);
    if (employee == null) {
      throw new NotFoundException("Ukendt medarbejder");
    }

    if (!allowAssignEmployeeToProject(loggedInUser)) {
      throw new OperationNotAllowedException("Kun projektleder kan tildele medarbejdere til projektet");
    }

    assignedEmployees.put(employee.getInitials(), employee);

  }

  private boolean allowAssignEmployeeToProject(Employee loggedInUser) {

    if (projectLeader == null) {
      return true;
    }
    if (loggedInUser.getInitials().equals(projectLeader.getInitials())) {
      return true;
    }
    return false;
  }

  public void createProjectActivity(String title, String startWeek, String endWeek, Employee loggedInUser)
      throws AlreadyExistsException, OperationNotAllowedException {
    if (projectLeader != null) {
      if (!projectLeader.getInitials().equals(loggedInUser.getInitials())) {
        throw new OperationNotAllowedException("Kun projektlederen kan redigere denne projekt aktivitet");
      }
    }

    if (hasProjectActivity(title)) {
      throw new AlreadyExistsException("Projekt aktivitet findes allerede");
    }

    ProjectActivity activity = new ProjectActivity(title, startWeek, endWeek);

    this.activities.add(activity);
  }

  public boolean hasProjectActivity(String title) {
    for (ProjectActivity projectActivity : this.activities) {
      if (projectActivity.getTitle().equals(title)) {
        return true;
      }
    }

    return false;
  }

  public List<ProjectActivity> getActivities() {
    return activities;
  }

  public ProjectActivity findProjectActivity(String title) throws NotFoundException {
    for (ProjectActivity projectActivity : this.activities) {
      if (projectActivity.getTitle().equals(title)) {
        return projectActivity;
      }
    }

    throw new NotFoundException("Projektaktiviteten findes ikke.");
  }

  public List<WorktimeRegistration> getWorktimeRegistrations() {

    List<WorktimeRegistration> list = new ArrayList<>();

    for (ProjectActivity projectActivity : this.activities) {
      list.addAll(projectActivity.getWorktimeRegistrations());
    }
    return list;

  }

  public List<Employee> getListOfAssignedEmployees() {
    return this.assignedEmployees.values().stream().toList();
  }

  public void addLatestReport(String date, Report report) {
    this.reports.put(date, report);
  }

  public Map<String, Report> getReports() {
    return reports;
  }

}
