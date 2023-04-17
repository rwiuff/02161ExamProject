package taskfusion.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import taskfusion.viewModels.ProjectActivityViewModel;

public class ProjectActivity extends Activity implements ConvertibleToViewModelInterface {
  
  private int timeBudget;
  private List<WorktimeRegistration> worktimeRegistrations;

  public ProjectActivity(String title, int startWeek, int endWeek) {
    super(title, startWeek, endWeek);
    this.timeBudget = 0;
    this.worktimeRegistrations = new ArrayList<>();
  }


  public ProjectActivityViewModel toViewModel() {
    return new ProjectActivityViewModel(this);
  }

  public int getTimeBudget() {
    return this.timeBudget;
  }

  public void setTimeBudget(int timeBudget) {
    this.timeBudget = timeBudget;
  }

  public void registerWorkTime(String initials, Calendar date, double workTime) {
    worktimeRegistrations.add(new WorktimeRegistration(initials, date, workTime));
  }


  public List<WorktimeRegistration> getWorktimeRegistrations() {
    return worktimeRegistrations;
  }

  public List<WorktimeRegistration> getWorkTimeRegistrationsForEmployee(String initials) {
    List<WorktimeRegistration> workTimes = new ArrayList<>();
    for (WorktimeRegistration registration : worktimeRegistrations) {
      if (registration.getInitials().equals(initials)) {
        workTimes.add(registration);
      }
    }
    return workTimes;
  }

  public double sumWorktime(List<WorktimeRegistration> worktimeRegistrations) {
    Double workTime = worktimeRegistrations.stream().mapToDouble(w -> w.getTime()).sum();
    return workTime;
  }

  public double getTotalWorkTimeForEmployee(String initials) {
    List<WorktimeRegistration> workTimeRegistrations = getWorkTimeRegistrationsForEmployee(initials);
    return sumWorktime(workTimeRegistrations);
  }

  public Double getTotalWorkTime() {
    return sumWorktime(worktimeRegistrations);
  }


public Double getRemainingWorktime() {
    return timeBudget - sumWorktime(worktimeRegistrations);
}
}
