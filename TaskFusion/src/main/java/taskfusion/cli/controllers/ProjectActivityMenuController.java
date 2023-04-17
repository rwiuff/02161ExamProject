package taskfusion.cli.controllers;

import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectActivityViewModel;

public class ProjectActivityMenuController implements ControllerInterface {

    private ProjectActivityViewModel activity;

    public ProjectActivityMenuController(ProjectActivityViewModel activity){
        this.activity = activity;
    }

    private String[] menuOptions = {
            "Se tidsregistreringer",
            "Opret tidsregistrering",
            "tilbage"
    };

    public void showMenu() {

        while (true) {
           
            int selectedMenuItem = Menu.showMenu(menuOptions, activity.title);

            switch (selectedMenuItem) {
                case 1: // Se tidsregistreringer
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 2: // Opret tidsregistreringer
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 3: //tilbage
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    } 


    
}
