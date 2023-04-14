package taskfusion.viewModels;

import taskfusion.domain.ProjectActivity;

public class ProjectActivityViewModel extends ViewModel {
    public String title;
    public int startWeek;
    public int endWeek;

    public ProjectActivityViewModel(ProjectActivity activity) {
        this.title = activity.getTitle();
        this.startWeek = activity.getStartWeek();
        this.endWeek = activity.getEndWeek();
    }
}
