package taskfusion.cli.controllers;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.CreateProjectView;
import taskfusion.cli.views.ListProjectsView;
import taskfusion.domain.Project;
import taskfusion.exceptions.NotFoundException;

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

                    Project selectedProject = null;
                    try {
                        selectedProject = new ListProjectsView().select();
                    } catch (NotFoundException e) {
                        Text.showExceptionError(e);
                    }    

                    if(selectedProject == null) {
                        continue;
                    }

                    new ProjectMenuController(selectedProject).showMenu();

                    break;

                case 2: // Opret projekt
                    new CreateProjectView().show();
                    break;

                case 3: // Se faste aktiviteter
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 4: // Opret fast aktivitet
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 5:
                    TaskFusionCLI.taskFusion().logout();
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

}
