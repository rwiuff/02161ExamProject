package taskfusion.cli.views;

import java.util.Map;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.ReportViewModel;

public class ProjectRaportView implements ViewInterface {

    private ProjectViewModel project;
    private Map<String, ReportViewModel> reports;

    public ProjectRaportView(ProjectViewModel project, Map<String, ReportViewModel> reports) {
        this.project = project;
        this.reports = reports;
    }

    public void show() {

        if(reports.size() == 0){
            Text.showInfo("Ingen rapporter at vise");
            Input.enterToContinue("Tryk på Enter for at gå videre");
        }

        

    }

    private void showReport() {
        
    }

    private void saveReport() {
        try {
            TaskFusionCLI.projectFacade().saveReport(project.projectNumber);
        } catch (Exception e) {
            Text.showExceptionError(e);
        }
    }

}
