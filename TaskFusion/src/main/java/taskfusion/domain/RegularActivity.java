package taskfusion.domain;

import taskfusion.viewModels.RegularActivityViewModel;

public class RegularActivity extends Activity implements ConvertibleToViewModelInterface {
  public RegularActivity(String title, int startWeek, int endWeek) {
    super(title, startWeek, endWeek);
  }

  public RegularActivityViewModel toViewModel() {
    return new RegularActivityViewModel(this);
  }

}
