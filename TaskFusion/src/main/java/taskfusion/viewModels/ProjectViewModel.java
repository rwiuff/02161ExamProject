package taskfusion.viewModels;
import taskfusion.domain.Project;

public class ProjectViewModel extends ViewModel {
    public String projectNumber;
    public String projectTitle;
    public String customer;
    public int startWeek;
    public int endWeek;

    public ProjectViewModel(Project project) {
        this.projectNumber = project.getProjectNumber();
        this.projectTitle = project.getProjectTitle();
        this.customer = project.getCustomer();
        this.startWeek = project.getStartWeek();
        this.endWeek = project.getEndWeek();
    }
}
