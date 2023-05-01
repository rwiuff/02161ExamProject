package taskfusion.cli.controllers;

import java.util.List;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.CreateProjectView;
import taskfusion.cli.views.CreateRegularActivityView;
import taskfusion.cli.views.ListProjectsView;
import taskfusion.cli.views.ListRegularActivitiesView;
import taskfusion.exceptions.NotFoundException;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.RegularActivityViewModel;

public class EmployeeMenuController implements ControllerInterface {

    private String[] menuOptions = {
            "Se projekter",
            "Opret projekt",
            "Se faste aktiviteter",
            "Opret fast aktivitet",
            "log ud"
    };

    public void showMenu() {

        while (true) {
            int selectedMenuItem = Menu.showMenu(menuOptions, "Medarbejder menu");

            switch (selectedMenuItem) {
                case 1: // Se projekter
                  
                    try {
                        List<ProjectViewModel> projects = TaskFusionCLI.taskFusion().getUserProjects();
                        new ListProjectsView(projects).show();
                    } catch (NotFoundException e) {
                        Text.showExceptionError(e);
                    }
                    
                    break;

                case 2: // Opret projekt
                    new CreateProjectView().show();
                    break;

                case 3: // Se faste aktiviteter
                    try {
                        List<RegularActivityViewModel> activities = TaskFusionCLI.taskFusion().getRegularActivities();
                        new ListRegularActivitiesView(activities).show();
                    } catch (NotFoundException e) {
                        Text.showExceptionError(e);
                    }
                    
                    break;

                case 4: // Opret fast aktivitet
                    new CreateRegularActivityView().show();
                    break;

                case 5:
                    TaskFusionCLI.taskFusion().logout();
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Ukendt menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

}
