package taskfusion.viewModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import taskfusion.domain.WorktimeRegistration;
import taskfusion.helpers.DateHelper;

public class WorktimeRegistrationViewModel extends ViewModel {
    public Integer id;
    public String initials;
    public double time;
    public Calendar date;
    public String dateString;

    public WorktimeRegistrationViewModel(WorktimeRegistration worktimeRegistration) {
        this.id = worktimeRegistration.getId();
        this.initials = worktimeRegistration.getInitials();
        this.time = worktimeRegistration.getTime();
        this.date = worktimeRegistration.getDate();
        this.dateString = DateHelper.getDateAsString(this.date);
    }

    public static List<WorktimeRegistrationViewModel> listFromModels(List<WorktimeRegistration> modelList) {

        List<WorktimeRegistrationViewModel> list = new ArrayList<WorktimeRegistrationViewModel>();

        for (WorktimeRegistration item : modelList) {
            list.add(item.toViewModel());
        }

        return list;
    }

}
