package taskfusion.viewModels;

import taskfusion.domain.RegularActivity;

public class RegularActivityViewModel extends ViewModel {
    public String title;
    public int startWeek;
    public int endWeek;

    public RegularActivityViewModel(RegularActivity activity) {
        this.title = activity.getTitle();
        this.startWeek = activity.getStartWeek();
        this.endWeek = activity.getEndWeek();
    }
}
