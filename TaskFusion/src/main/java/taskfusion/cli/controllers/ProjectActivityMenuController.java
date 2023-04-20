package taskfusion.cli.controllers;

import java.util.List;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.CreateWorktimeRegistrationView;
import taskfusion.cli.views.ListWorktimeRegistrationsView;
import taskfusion.viewModels.ProjectActivityViewModel;
import taskfusion.viewModels.ProjectViewModel;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class ProjectActivityMenuController implements ControllerInterface {

    private ProjectActivityViewModel activity;
    private ProjectViewModel project;

    public ProjectActivityMenuController(ProjectViewModel project, ProjectActivityViewModel activity) {
        this.activity = activity;
        this.project = project;
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
                    try {
                        List<WorktimeRegistrationViewModel> registrations = TaskFusionCLI.projectFacade()
                                .getUserWorktimeRegistrationsForProjectActivity(activity.title, project.projectNumber);
                        new ListWorktimeRegistrationsView(registrations).show();
                    } catch (Exception e) {
                        Text.showExceptionError(e);
                        break;
                    }

                    break;

                case 2: // Opret tidsregistreringer
                    new CreateWorktimeRegistrationView(project, activity).show();
                    break;

                case 3: // tilbage
                    return; // NOTICE THIS RETURN, not break

                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

}
