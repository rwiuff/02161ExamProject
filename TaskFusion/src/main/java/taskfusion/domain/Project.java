package taskfusion.domain;

import taskfusion.persistency.ProjectRepository;

public class Project {
  private int projectNumber;
  private String projectTitle;
  private String customer;
  private boolean internal;
  private int startWeek;
  private int endWeek;

  public Project(String projectTitle, int year) {
    this.projectTitle = projectTitle;
    this.projectNumber = Project.generateProjectNumber(year);
    
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

  public int getProjectNumber() {
    return this.projectNumber;
  }

  public void setProjectNumber(int projectNumber) {
    this.projectNumber = projectNumber;
  }

  public static int generateProjectNumber(int year) {
    ProjectRepository projectRepo = ProjectRepository.getInstance();


    return (Integer.parseInt(year + "000") + (projectRepo.projects.size() + 1));



  }

}
