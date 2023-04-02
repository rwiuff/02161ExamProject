package taskfusion.cli.controllers;

import taskfusion.app.TaskFusion;
import taskfusion.cli.components.Menu;
import taskfusion.cli.views.LoginView;
import taskfusion.cli.views.RegisterEmployeeView;

public class GuestMenuController extends Controller  {
    
    private String[] menuOptions = {
        "Login",
        "Opret medarbejder",
        "Luk"
    };

    public GuestMenuController(TaskFusion taskFusion){
        super(taskFusion);
    }

    public void showMenu() {
        
        int selectedMenuItem = 0;
        do {
            selectedMenuItem = Menu.showMenu(menuOptions, "Gæst menu");

            switch (selectedMenuItem) {
                case 1:
                    LoginView loginView = new LoginView();
                    loginView.show();
                    break;
                case 2:
                    RegisterEmployeeView registerEmployeeView = new RegisterEmployeeView();
                    registerEmployeeView.show();
                    break;
                case 3:
                    selectedMenuItem = 0;
                    System.out.print("--- FARVEL ---");
                    break;
                default:
                    break;
            }
        } while(selectedMenuItem != 0);

    }
}
