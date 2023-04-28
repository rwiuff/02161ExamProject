package taskfusion.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
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
    int num = 0;

    // Find the highest incremental number for the current year
    for (String projectNumber : ProjectRepository.getInstance().all().keySet()) {
      if (projectNumber.startsWith(String.format("%02d", year))) {
        num = Integer.parseInt(projectNumber.substring(2));
      }
    }

    int nextProjectNumber = (year * 1000) + num + 1;

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

  public ProjectActivity createProjectActivity(String title, String startWeek, String endWeek, Employee loggedInUser)
      throws AlreadyExistsException, OperationNotAllowedException, InvalidPropertyException {

    /**
     * NOTE: Assertions are disabled using comments,
     * as they are not properly managed in code doverage report.
     */

    // assert title != null;
    // assert startWeek != null;
    // assert endWeek != null;
    // assert loggedInUser != null;
    // assert activities != null;

    if (hasProjectLeader()) {
      if (!projectLeader.isSameAs(loggedInUser)) {
        throw new OperationNotAllowedException("Kun projektlederen kan oprette en projekt aktivitet for dette projekt");
      }
    }

    if (hasProjectActivity(title)) {
      throw new AlreadyExistsException("Projekt aktivitet findes allerede");
    }

    ProjectActivity activity = new ProjectActivity(title, startWeek, endWeek);
    this.activities.add(activity);

    /**
     * NOTE: This post condition does not check for
     * !project@pre.hasProjectActivity(title)
     */
    // assert (
    // activities.contains(activity) &&
    // (!hasProjectLeader() || projectLeader.isSameAs(loggedInUser) )
    // );

    return activity;
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

  public boolean hasProjectLeader() {
    return projectLeader != null;
  }

  public ProjectActivity findProjectActivity(String title) throws NotFoundException {
    for (ProjectActivity projectActivity : this.activities) {
      if (projectActivity.getTitle().equals(title)) {
        return projectActivity;
      }
    }
    throw new NotFoundException("Projektaktiviteten findes ikke.");
  }

  public List<WorktimeRegistration> getWorktimeRegistrations() throws NotFoundException {

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

  public void setActivityTimeBudget(Employee employee, String projectActivityTitle, Integer timeBudget) throws OperationNotAllowedException, NotFoundException {
    if (projectLeader == null || projectLeader.isSameAs(employee)) {
      findProjectActivity(projectActivityTitle).setTimeBudget(timeBudget);
    } else {
      throw new OperationNotAllowedException("Kun projektlederen kan tildele tidsbudgetter");
    }
  }

}
