package taskfusion.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
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

  public static String generateProjectNumber(Calendar date) {
    ProjectRepository projectRepo = ProjectRepository.getInstance();

    int year = date.get(Calendar.YEAR) % 100;

    if (projectRepo.projects.isEmpty()) {
      return year + "001";
    }
    String[] serials = (String[]) projectRepo.projects.keySet().toArray();
    int highestSerial = 0;
    int tmp;
    for (String serial : serials) {
      if (serial.contains("" + year)) {
        tmp = Integer.parseInt(serial.substring(2, 4));
        highestSerial = (highestSerial < tmp) ? tmp : highestSerial;
      }
    }
    String projectNumber = String.format("%3d", year + highestSerial);
    return projectNumber;

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
    Employee employee = EmployeeRepository.getInstance().employees.get(employeeInitials);
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
