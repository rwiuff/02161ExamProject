package taskfusion.cli.controllers;

import java.util.List;
import java.util.Map;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.AssignEmployeeToProjectView;
import taskfusion.cli.views.CreateProjectActivityView;
import taskfusion.cli.views.GenerateProjectReportView;
import taskfusion.cli.views.ListEmployeesView;
import taskfusion.cli.views.ListProjectActivitiesView;
import taskfusion.cli.views.ListProjectReportView;
import taskfusion.cli.views.ProjectInfoView;
import taskfusion.cli.views.TakeProjectLeaderRoleView;
import taskfusion.exceptions.NotFoundException;
import taskfusion.viewModels.EmployeeViewModel;
import taskfusion.viewModels.ProjectActivityViewModel;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.ReportViewModel;

public class ProjectMenuController implements ControllerInterface {

    private ProjectViewModel project;

    public ProjectMenuController(ProjectViewModel project) {
        this.project = project;
    }

    private String[] menuOptions = {
            "Se medarbejdere",
            "Tilføj medarbejder til projektet",
            "Påtag projektleder rolle",
            "Se projekt aktiviteter",
            "Tilføj projekt aktivitet",
            "Opret projekrapport",
            "Projektrapporter",
            "tilbage"
    };

    public void showMenu() {

        if (project == null) {
            Text.showError("Ugyldigt projekt");
            return;
        }

        while (true) {
            new ProjectInfoView(project).show();

            int selectedMenuItem = Menu.showMenu(menuOptions, "Projekt menu");

            switch (selectedMenuItem) {
                case 1: // Se medarbejdere
                    List<EmployeeViewModel> employees = TaskFusionCLI.taskFusion()
                            .getProjectEmployees(project.projectNumber);
                    new ListEmployeesView(employees).show();
                    break;

                case 2: // Tilføj medarbejder til projekt
                    new AssignEmployeeToProjectView(project).show();
                    reloadProject();
                    break;

                case 3: // Påtag projektlederrolle
                    new TakeProjectLeaderRoleView(project).show();
                    reloadProject();
                    break;

                case 4: // Se projekt aktiviteter
                    List<ProjectActivityViewModel> activities = project.projectActivities;
                    new ListProjectActivitiesView(activities, project).show();
                    break;

                case 5: // Tilføj projekt aktivitet
                    new CreateProjectActivityView(project).show();
                    reloadProject();
                    break;

                case 6: // Opret projekrapport
                    new GenerateProjectReportView(project).show();
                    reloadProject();
                    break;

                case 7: // Projektrapporter
                    Map<String, ReportViewModel> reports = project.reports;
                    new ListProjectReportView(reports).show();
                    break;
                case 8:
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

    private void reloadProject() {

        ProjectViewModel reloadedProject = null;
        try {
            reloadedProject = TaskFusionCLI.taskFusion().findProjectByProjectNumber(project.projectNumber);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return;
        }
        this.project = reloadedProject;
    }

}
