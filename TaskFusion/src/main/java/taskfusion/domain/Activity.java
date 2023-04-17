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

  public void setStartWeek(String startWeek) {
    this.startWeek = startWeek;
  }

  public String getEndWeek() {
    return this.endWeek;
  }

  public void setEndWeek(String endWeek) {
    this.endWeek = endWeek;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
