package taskfusion.domain;

import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.persistency.EmployeeRepository;
import taskfusion.viewModels.RegularActivityViewModel;

public class RegularActivity extends Activity implements ConvertibleToViewModelInterface {
  private int id;

  public RegularActivity(String title, String startWeek, String endWeek) throws InvalidPropertyException {
    super(title, startWeek, endWeek);
    this.id = EmployeeRepository.getInstance().generateRegularActivityId();
  }

  public RegularActivityViewModel toViewModel() {
    return new RegularActivityViewModel(this);
  }

  public Integer getId() {
    return id;
  }

}
