package taskfusion.domain;

import java.util.Calendar;
import java.util.Set;

import taskfusion.persistency.ProjectRepository;

public class Project {
  private String projectNumber;
  private String projectTitle;
  private String customer;
  private boolean internal;
  private int startWeek;
  private int endWeek;

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

    if(projectRepo.projects.isEmpty()){
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

}
