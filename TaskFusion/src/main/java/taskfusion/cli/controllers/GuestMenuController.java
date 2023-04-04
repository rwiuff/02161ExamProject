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
                    new LoginView().show();

                    if (TaskFusionCLI.taskFusion().isLoggedIn()) {
                        new EmployeeMenuController().showMenu();
                    }

                    break;
                case 2:
                    new RegisterEmployeeView().show();
                    break;
                case 3:
                    System.out.println("--- FARVEL ---");
                    return; // NOTICE THIS RETURN, not break
                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

}
