package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectActivityViewModel;
import taskfusion.viewModels.ProjectViewModel;

public class CreateWorktimeRegistrationView implements ViewInterface {
    
    private ProjectActivityViewModel activity;
    private ProjectViewModel project;

    public CreateWorktimeRegistrationView(ProjectViewModel project, ProjectActivityViewModel activity) {
        this.activity = activity;
        this.project = project;
    }

    public void show() {

        Header.showHeader("Opret tidsregistrering", 1);

        while(true) {
            
            String time = Input.lineWithCancel("Tidsforbrug");
            
            if(time == null) {
                return;
            }

            try {
                TaskFusionCLI.taskFusion().registerWorkTime(project.projectNumber, activity.title, Double.parseDouble(time));
            } catch (Exception e) {
                Text.showExceptionError(e);
                Text.showInfo("Prøv igen");
                continue;
            }

            Text.showSuccess("Tidsregistrering oprettet.");
            return;
        }
    }
}
