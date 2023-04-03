package taskfusion.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.helpers.DateHelper;
import taskfusion.helpers.PrintHelper;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class Project {
  private String projectNumber;
  private String projectTitle;
  private String customer;
  private Employee projectLeader;
  private boolean internal;
  private int startWeek;
  private int endWeek;
  private Map<String, Employee> assignedEmployees = new HashMap<>();

  public Project(String projectTitle, Calendar date) {
    this.projectTitle = projectTitle;
    this.projectNumber = generateProjectNumber(date);
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
    return this.internal;
  }

  public void setInternalExternal(boolean internal) {
    this.internal = internal;
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

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  public String getProjectNumber() {
    return this.projectNumber;
  }

  public void setProjectNumber(String projectNumber) {
    this.projectNumber = projectNumber;
  }

  /**
   * Generate a unique 5 digit project number as a string. Always leading with two-digit-year
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

  public void assignEmployee(String employeeInitials, Employee loggedInUser)
      throws NotFoundException, OperationNotAllowedException {
    Employee employee = EmployeeRepository.getInstance().findByInitials(employeeInitials);
    if (projectLeader == null) {
      assignedEmployees.put(employee.getInitials(), employee);
      return;
    }
    if (loggedInUser.getInitials().equals(projectLeader.getInitials())) {
      assignedEmployees.put(employee.getInitials(), employee);
      return;
    }
    throw new OperationNotAllowedException("Kun projektleder kan tildele medarbejdere til projektet");

  }

}
