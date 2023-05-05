package taskfusion.domain;

import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.viewModels.ProjectActivityViewModel;

public class ProjectActivity extends ActivityWithWorktimeRegistrations implements ConvertibleToViewModelInterface {


  public ProjectActivity(String title, String startWeek, String endWeek) throws InvalidPropertyException {
    super(title, startWeek, endWeek);
  }

  public ProjectActivityViewModel toViewModel() {
    return new ProjectActivityViewModel(this);
  }


}
