package taskfusion.cli.controllers;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.LoginView;
import taskfusion.cli.views.RegisterEmployeeView;

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
                case 1:
                    showListProjects();
                    break;
                case 2:
                    showCreateProject();
                    break;
                case 3:
                    showListRegularActivities();
                    break;
                case 4:
                    showCreateRegularActivity();
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

    /**
     * SHOW pages
     */

    private void showListProjects() {
        Text.showError("MANGLER IMPLEMENTERING");
    }

    private void showCreateProject() {
        Text.showError("MANGLER IMPLEMENTERING");
    }

    private void showListRegularActivities() {
        Text.showError("MANGLER IMPLEMENTERING");
    }

    private void showCreateRegularActivity() {
        Text.showError("MANGLER IMPLEMENTERING");
    }

}
