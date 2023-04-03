package taskfusion.cli.controllers;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.views.LoginView;
import taskfusion.cli.views.RegisterEmployeeView;

public class GuestMenuController implements ControllerInterface {
    
    private String[] menuOptions = {
        "Login",
        "Opret medarbejder",
        "Luk"
    };

    public void showMenu() {
        
        int selectedMenuItem = 0;
        do {
            selectedMenuItem = Menu.showMenu(menuOptions, "Gæst menu");

            switch (selectedMenuItem) {
                case 1:
                    showLogin();
                    break;
                case 2:
                    showRcreate();
                    break;
                case 3:
                    showQuit();
                    break;
                default:
                    break;
            }
        } while(selectedMenuItem != 3);
    }

    /**
     * SHOW pages
     */

    private void showLogin() {
        LoginView loginView = new LoginView();
        loginView.show();
        if(TaskFusionCLI.taskFusion().isLoggedIn()) {
            System.out.println(("SHOW LOGGED IN MENU"));
            while(true){

            }
        }
    }

    private void showRcreate() {
        RegisterEmployeeView registerEmployeeView = new RegisterEmployeeView();
        registerEmployeeView.show();
    }

    private void showQuit() {
        System.out.print("--- FARVEL ---");
    }

}
