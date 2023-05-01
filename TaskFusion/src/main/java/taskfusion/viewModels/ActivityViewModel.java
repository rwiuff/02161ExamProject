package taskfusion.viewModels;

import taskfusion.domain.Activity;

public class ActivityViewModel extends ViewModel {
  public String title;
  public String startWeek;
  public String endWeek;

  public ActivityViewModel(Activity activity) {
    this.title = activity.getTitle();
    this.startWeek = activity.getStartWeek();
    this.endWeek = activity.getEndWeek();
  }

}
