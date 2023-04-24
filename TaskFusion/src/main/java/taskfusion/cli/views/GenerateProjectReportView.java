package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.ReportViewModel;

public class GenerateProjectReportView implements ViewInterface {

    private ProjectViewModel project;
    private ReportViewModel report;

    public GenerateProjectReportView(ProjectViewModel project) {
        this.project = project;
    }

    @Override
    public void show() {
        if(project.projectLeaderFullName == null){
            Text.showError("Projektet mangler en projektleder for at genererer rapporter");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }
        if(!TaskFusionCLI.taskFusion().getLoggedInUser().fullName.equals(project.projectLeaderFullName)){
            Text.showError("Kun projektleder kan genererer rapporter");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }
        Text.showInfo("Genererer projektrepport");
        generateReport();
    }

    private void generateReport() {
        try {
            report = TaskFusionCLI.projectFacade().generateProjectRaport(project.projectNumber);
            Text.showSuccess("Rapport genereret");
            Input.enterToContinue("Tryk på Enter for at se rapporten");
            new ProjectReportView(report).show();
        } catch (Exception e) {
            Text.showExceptionError(e);
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
        }
    }
}