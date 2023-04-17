package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.List;

import taskfusion.domain.RegularActivity;

public class RegularActivityViewModel extends ViewModel {
    public int id;
    public String title;
    public int startWeek;
    public int endWeek;

    public RegularActivityViewModel(RegularActivity activity) {
        this.id = activity.getId();
        this.title = activity.getTitle();
        this.startWeek = activity.getStartWeek();
        this.endWeek = activity.getEndWeek();
    }

    public static List<RegularActivityViewModel> listFromModels(List<RegularActivity> regularActivityList) {
      List<RegularActivityViewModel> list = new ArrayList<RegularActivityViewModel>();

      for(RegularActivity regularActivity : regularActivityList) {
        list.add(regularActivity.toViewModel());
      }

      return list;
    }
}
