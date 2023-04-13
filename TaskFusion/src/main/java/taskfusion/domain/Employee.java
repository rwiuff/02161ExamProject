package taskfusion.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.persistency.ProjectRepository;

public class Employee {
  private List<RegularActivity> regularActivities = new ArrayList<RegularActivity>();
  private Map<String, Project> projects = new HashMap<>();
  private String firstName;
  private String lastName;
  private String initials;

  public Employee(String firstName, String lastName) throws InvalidPropertyException, ExhaustedOptionsException {
    this.firstName = validateFirstName(firstName);
    this.lastName = validateLastName(lastName);
    createInitials();

  }

  private String validateFirstName(String firstName) throws InvalidPropertyException {
    if (firstName.length() < 2) {
      throw new InvalidPropertyException("Fornavn mangler");
    }
    return firstName;
  }

  private String validateLastName(String lastName) throws InvalidPropertyException {
    if (lastName.length() < 2) {
      throw new InvalidPropertyException("Efternavn mangler");
    }
    return lastName;
  }

  /*
   * Method generates unique initials based on first- and lastName.
   * Unique initials is assured by comparing generated initials to existing
   * initials in the employee repo
   * The following logic is followed:
   * First 2 letters of firstName is always used, then adding:
   * Letter 1 and 2 from lastName is tried
   * Then letter 1 and 3, Then 1 and 4 and so on.
   * If last name is exhuseted, then add letter 2 and 3, then 2 and 4 and so on
   * from the last name.
   * 
   * Solution using 3 nested for loops, is generated by ChatGPT v.4
   */
  private void createInitials() throws ExhaustedOptionsException {

    // String init = firstName.substring(0, 2) + lastName.substring(0, 2);
    // this.initials = init.toLowerCase();

    EmployeeRepository employeeRepo = EmployeeRepository.getInstance();

    for (int l1 = 0; l1 < lastName.length(); l1++) {
      for (int l2 = l1 + 1; l2 < lastName.length(); l2++) {
        String init = firstName.substring(0, 2)
            + lastName.substring(l1, Math.min(l1 + 1, lastName.length()))
            + lastName.substring(l2, Math.min(l2 + 1, lastName.length()));

        init = init.toLowerCase();
        if (employeeRepo.findByInitials(init) == null) {
          this.initials = init;
          return;
        }
      }
    }

    throw new ExhaustedOptionsException("Kunne ikke generere unikke initialer");

  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public String getInitials() {
    return initials;
  }

  public List<RegularActivity> getRegularActivities() {
    return this.regularActivities;
  }

  public void addRegularActivity(RegularActivity regularActivity) {
    this.regularActivities.add(regularActivity);
  }

  public boolean hasRegularActivity(String title, int startWeek, int endWeek) {
    for (RegularActivity regularActivity : this.regularActivities) {
      if (regularActivity.getTitle() == title && regularActivity.getStartWeek() == startWeek
          && regularActivity.getEndWeek() == endWeek) {
        return true;
      }
    }

    return false;
  }

  public Map<String, Project> getProjects() {

    Map<String, Project> employeeProjects = new HashMap<>();

    for (Map.Entry<String, Project> projectEntry : ProjectRepository.getInstance().all().entrySet()) {

        Project project = projectEntry.getValue();

        if(project.getAssignedEmployee(initials) != null) {
          employeeProjects.put(project.getProjectNumber(),project);
        }
    }

    return employeeProjects;

  }

  public Project findProject(String projectNumber) {
    return this.getProjects().get(projectNumber);
  }

  public void addProject(Project project) throws AlreadyExistsException, NotFoundException {
    if (findProject(project.getProjectNumber()) == null) {
      this.projects.put(project.getProjectNumber(), project);
    } else {
      throw new AlreadyExistsException("Projektet findes allerede");
    }
  }

  public void addProjectActivity(String projectNumber, ProjectActivity projectActivity) throws AlreadyExistsException, NotFoundException {
    Project project = findProject(projectNumber);
    project.createProjectActivity(projectActivity);
  }

  public void setTimeBudgetProjectActivity(String projectNumber, String title, Integer timeBudget) throws NotFoundException {
    Project project = findProject(projectNumber);
    project.findProjectActivity(title).setTimeBudget(timeBudget);
  }
}
