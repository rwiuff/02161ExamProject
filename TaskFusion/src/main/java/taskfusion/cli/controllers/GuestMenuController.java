package taskfusion.cli.controllers;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.LoginView;
import taskfusion.cli.views.RegisterEmployeeView;

public class GuestMenuController implements ControllerInterface {

    private String[] menuOptions = {
            "Login",
            "Opret medarbejder",
            "Luk"
    };

    public void showMenu() {

        while (true) {
            int selectedMenuItem = Menu.showMenu(menuOptions, "Gæst menu");

            switch (selectedMenuItem) {
                case 1:
                    showLogin();
                    break;
                case 2:
                    showRcreate();
                    break;
                case 3:
                    showQuit();
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

    private void showLogin() {
        LoginView loginView = new LoginView();
        loginView.show();
        if (TaskFusionCLI.taskFusion().isLoggedIn()) {
            new EmployeeMenuController().showMenu();
        }
    }

    private void showRcreate() {
        RegisterEmployeeView registerEmployeeView = new RegisterEmployeeView();
        registerEmployeeView.show();
    }

    private void showQuit() {
        System.out.println("--- FARVEL ---");
    }

}
