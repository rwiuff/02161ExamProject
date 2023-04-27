package taskfusion.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.ProjectActivityViewModel;

public class ProjectActivity extends Activity implements ConvertibleToViewModelInterface {

  private int timeBudget;
  private List<WorktimeRegistration> worktimeRegistrations;

  public ProjectActivity(String title, String startWeek, String endWeek) throws InvalidPropertyException {
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

  public void registerWorkTime(Employee employee, Calendar date, double workTime)
      throws NotFoundException, OperationNotAllowedException {
    List<RegularActivity> regularActivities = employee.getRegularActivities();
    boolean hasRegularActivities = false;
    String activityTitle = "";
    for (RegularActivity activity : regularActivities) {
      int startWeek = Integer.parseInt(activity.startWeek.substring(2, 4));
      int endWeek = Integer.parseInt(activity.endWeek.substring(2, 4));
      if (startWeek >= Integer.parseInt(super.startWeek.substring(2, 4))
          && endWeek <= Integer.parseInt(super.endWeek.substring(2, 4))) {
        hasRegularActivities = true;
        activityTitle = activity.title;
      }
    }
    if (hasRegularActivities) {
      throw new OperationNotAllowedException("Medarbejderen er optaget af den faste aktivitet: " + activityTitle);
    } else {
      worktimeRegistrations.add(new WorktimeRegistration(employee.getInitials(), date, workTime));
    }
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
