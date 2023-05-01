package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectActivityViewModel;
import taskfusion.viewModels.ProjectViewModel;

public class SetTimeBudgetView implements ViewInterface {
    
    private ProjectActivityViewModel activity;
    private ProjectViewModel project;

    public SetTimeBudgetView(ProjectViewModel project, ProjectActivityViewModel activity) {
        this.activity = activity;
        this.project = project;
    }

    public void show() {

        Header.showHeader("Angiv budgeteret tid", 1);

        while(true) {
            
            String time = Input.lineWithCancel("Budgeteret tid");
            
            if(time == null) {
                return;
            }

            try {
                TaskFusionCLI.taskFusion().setTimeBudget(project.projectNumber, activity.title, Integer.parseInt(time));
            } catch (Exception e) {
                Text.showExceptionError(e);
                Text.showInfo("Prøv igen");
                continue;
            }

            Text.showSuccess("Budgeteret tid sat.");
            return;
        }
    }
}
