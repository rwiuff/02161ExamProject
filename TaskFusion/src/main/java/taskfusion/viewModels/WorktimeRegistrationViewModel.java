package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import taskfusion.domain.WorktimeRegistration;

public class WorktimeRegistrationViewModel extends ViewModel {
    public Integer id;
    public String initials;
    public double time;
    public Calendar date;

    public WorktimeRegistrationViewModel(WorktimeRegistration worktimeRegistration) {
        this.id = worktimeRegistration.getId();
        this.initials = worktimeRegistration.getInitials();
        this.time = worktimeRegistration.getTime();
        this.date = worktimeRegistration.getDate();
    }

    public static List<WorktimeRegistrationViewModel> listFromModels(List<WorktimeRegistration> modelList) {
        
        List<WorktimeRegistrationViewModel> list = new ArrayList<WorktimeRegistrationViewModel>();

        for(WorktimeRegistration item : modelList) {
            list.add(item.toViewModel());
        }

        return list;
    }
}
