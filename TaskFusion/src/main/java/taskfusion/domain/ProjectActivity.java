package taskfusion.domain;

public class ProjectActivity extends Activity {
  private int timeBudget;

  public ProjectActivity(String title, int startWeek, int endWeek) {
    super(title, startWeek, endWeek);
  }

  public void setTimeBudget(int hours) {
    this.timeBudget = hours;
  }

  public int getTimeBudget(){
    return this.timeBudget;
  }
}
