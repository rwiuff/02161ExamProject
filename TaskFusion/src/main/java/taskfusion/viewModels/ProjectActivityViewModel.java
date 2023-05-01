package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.List;

import taskfusion.domain.ProjectActivity;

public class ProjectActivityViewModel extends ActivityViewModel {
  public double timeBudget;
  public double totalWorktime;
  public List<WorktimeRegistrationViewModel> worktimeRegistrations;

  public ProjectActivityViewModel(ProjectActivity activity) {
    super(activity);
    this.timeBudget = activity.getTimeBudget();
    this.totalWorktime = activity.getTotalWorkTime();
    this.worktimeRegistrations = WorktimeRegistrationViewModel.listFromModels(activity.getWorktimeRegistrations());
  }

  public static List<ProjectActivityViewModel> listFromModels(List<ProjectActivity> projectActivityList) {
    List<ProjectActivityViewModel> list = new ArrayList<ProjectActivityViewModel>();

    for (ProjectActivity projectActivity : projectActivityList) {
      list.add(projectActivity.toViewModel());
    }

    return list;
  }
}
