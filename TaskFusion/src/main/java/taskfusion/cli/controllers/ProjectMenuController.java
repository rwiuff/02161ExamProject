package taskfusion.cli.controllers;

import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.ProjectInfoView;
import taskfusion.viewModels.ProjectViewModel;

public class ProjectMenuController implements ControllerInterface {

    private ProjectViewModel project;

    public ProjectMenuController(ProjectViewModel project){
        this.project = project;
    }


    private String[] menuOptions = {
            "Se medarbejdere",
            "Tilføj medarbejder til projektet",
            "Påtag projektleder rolle",
            "tilbage"
    };

    public void showMenu() {

        if(project == null) {
            Text.showError("Ugyldigt projekt");
            return;
        }

        while (true) {
            new ProjectInfoView(project).show();
            
            int selectedMenuItem = Menu.showMenu(menuOptions, "Projekt menu");

            switch (selectedMenuItem) {
                case 1: //Se medarbejdere
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 2: // Tilføj medarbejder til projekt
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 3: // Påtag projektlederrolle
                    Text.showError("MANGLER IMPLEMENTERING");
                    break;

                case 4:
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    } 
    
}
