package taskfusion.domain;

public abstract class Activity {
  protected String title;
  protected int startWeek;
  protected int endWeek;
  protected int timeBudget;

  public Activity(String title, Integer startWeek, Integer endWeek) {
    this.title = title;
    this.startWeek = startWeek;
    this.endWeek = endWeek;
    this.timeBudget = 0;
  }

  public int getStartWeek() {
    return this.startWeek;
  }

  public void setStartWeek(int startWeek) {
    this.startWeek = startWeek;
  }

  public int getEndWeek() {
    return this.endWeek;
  }

  public void setEndWeek(int endWeek) {
    this.endWeek = endWeek;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getTimeBudget() {
    return this.timeBudget;
  }

  public void setTimeBudget(int timeBudget) {
    this.timeBudget = timeBudget;
  }
}
