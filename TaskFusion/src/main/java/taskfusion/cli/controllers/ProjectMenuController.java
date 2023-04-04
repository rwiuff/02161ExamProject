package taskfusion.cli.controllers;

import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.domain.Project;

public class ProjectMenuController implements ControllerInterface {

    private Project project;

    public ProjectMenuController(Project project){
        this.project = project;
    }


    private String[] menuOptions = {
            "Se medarbejdere",
            "Påtag projektleder rolle",
            "tilbage"
    };

    public void showMenu() {

        if(project == null) {
            Text.showError("Ugyldigt projekt");
            return;
        }

        while (true) {
            int selectedMenuItem = Menu.showMenu(menuOptions, "Projekt menu");

            switch (selectedMenuItem) {
                case 1: //Se medarbejdere
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 2: // Påtag projektlederrolle
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 3:
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    } 
    
}
