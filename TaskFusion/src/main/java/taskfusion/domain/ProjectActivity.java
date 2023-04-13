package taskfusion.domain;

import java.util.HashMap;
import java.util.Map;

public class ProjectActivity extends Activity {
  protected int timeBudget;

  private Map<String, Double> timeRegister = new HashMap<>();

  public ProjectActivity(String title, int startWeek, int endWeek) {
    super(title, startWeek, endWeek);
    this.timeBudget = 0;
  }

  public int getTimeBudget() {
    return this.timeBudget;
  }

  public void setTimeBudget(int timeBudget) {
    this.timeBudget = timeBudget;
  }

  public void registerWorkTime(String initials, double worktTime) {
    if (timeRegister.get(initials) == null) {
      timeRegister.put(initials, worktTime);
    } else {
      timeRegister.put(initials, timeRegister.get(initials) + worktTime);
    }
  }

  public double getWorkTime(String initials) {
    return timeRegister.get(initials);
  }
}
