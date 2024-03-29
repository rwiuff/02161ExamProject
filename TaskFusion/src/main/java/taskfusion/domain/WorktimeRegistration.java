package taskfusion.domain;

import java.util.Calendar;

import taskfusion.exceptions.NotFoundException;
import taskfusion.persistency.ProjectRepository;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class WorktimeRegistration implements ConvertibleToViewModelInterface {
    private Integer id;
    private Calendar date;
    private String initials;
    private double time;

    public WorktimeRegistration(String initials, Calendar date, double time) throws NotFoundException {
        this.initials = initials;
        this.date = date;
        this.time = time;
        this.id = ProjectRepository.getInstance().generateWorktimeRegistrationId();
    }

    public WorktimeRegistrationViewModel toViewModel() {
        return new WorktimeRegistrationViewModel(this);
    }

    public Calendar getDate() {
        return date;
    }

    public String getInitials() {
        return initials;
    }

    public double getTime() {
        return time;
    }

    public Integer getId() {
        return id;
    }

    public void setTime(double time) {
        this.time = time;
    }

}
