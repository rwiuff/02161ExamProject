package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;

public class CreateProjectView implements ViewInterface {
    
    public void show() {

        Header.showHeader("Opret projekt", 1);

        while(true) {
            
            String projectTitle = Input.lineWithCancel("Projekt titel");
            
            if(projectTitle == null) {
                return;
            }

            try {
                TaskFusionCLI.taskFusion().createProject(projectTitle);
            } catch (Exception e) {
                Text.showExceptionError(e);
                Text.showInfo("Prøv igen");
                continue;
            }

            Text.showSuccess("Projekt oprettet.");
            return;
        }
    }
}
