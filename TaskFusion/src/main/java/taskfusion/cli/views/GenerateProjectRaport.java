package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.ReportViewModel;

public class GenerateProjectRaport {

    private ProjectViewModel project;
    private ReportViewModel report;

    public GenerateProjectRaport(ProjectViewModel project) {
        this.project = project;
    }

    public void show() {

        Header.showHeader("Genererer projektrapport for \"" + project.projectTitle + "\", gem rapport?", 1);
        try {
            report = TaskFusionCLI.projectFacade().generateProjectRaport(project.projectNumber);
        } catch (Exception e) {
            Text.showExceptionError(e);
            return;
        }
        boolean confirm = Input.confirm();

        if (confirm) {
            try {
                showReport();
                saveReport();
                Text.showSuccess("Rapport gemt");
            } catch (Exception e) {
                Text.showExceptionError(e);
                return;
            }
        } else {
            try {
                showReport();
            } catch (Exception e) {
                Text.showExceptionError(e);
                return;
            }
        }

    }

    private void showReport() {
        
    }

    private void saveReport() {
    }

}
