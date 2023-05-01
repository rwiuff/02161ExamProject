package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.List;

import taskfusion.domain.RegularActivity;

public class RegularActivityViewModel extends ActivityViewModel {
  public int id;

  public RegularActivityViewModel(RegularActivity activity) {
    super(activity);
    this.id = activity.getId();
  }

  public static List<RegularActivityViewModel> listFromModels(List<RegularActivity> regularActivityList) {
    List<RegularActivityViewModel> list = new ArrayList<RegularActivityViewModel>();

    for (RegularActivity regularActivity : regularActivityList) {
      list.add(regularActivity.toViewModel());
    }

    return list;
  }
}
