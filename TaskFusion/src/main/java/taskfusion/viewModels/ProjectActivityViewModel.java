package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.List;

import taskfusion.domain.ProjectActivity;

public class ProjectActivityViewModel extends ViewModel {
    public String title;
    public String startWeek;
    public String endWeek;

    public ProjectActivityViewModel(ProjectActivity activity) {
        this.title = activity.getTitle();
        this.startWeek = activity.getStartWeek();
        this.endWeek = activity.getEndWeek();
    }

    public static List<ProjectActivityViewModel> listFromModels(List<ProjectActivity> projectActivityList) {
        List<ProjectActivityViewModel> list = new ArrayList<ProjectActivityViewModel>();
  
        for(ProjectActivity projectActivity : projectActivityList) {
          list.add(projectActivity.toViewModel());
        }
  
        return list;
      }
}
