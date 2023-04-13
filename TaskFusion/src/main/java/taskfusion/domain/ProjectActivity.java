package taskfusion.domain;

public class ProjectActivity extends Activity {
  protected int timeBudget;

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
}
