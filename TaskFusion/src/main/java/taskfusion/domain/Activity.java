package taskfusion.domain;

public abstract class Activity {
  protected String title;
  protected String startWeek;
  protected String endWeek;

  public Activity(String title, String startWeek, String endWeek) {
    this.title = title;
    this.startWeek = startWeek;
    this.endWeek = endWeek;
  }

  public String getStartWeek() {
    return this.startWeek;
  }

  public String getEndWeek() {
    return this.endWeek;
  }

  public String getTitle() {
    return this.title;
  }

}
