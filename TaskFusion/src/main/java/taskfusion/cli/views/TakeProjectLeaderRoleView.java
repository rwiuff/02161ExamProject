package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectViewModel;

public class TakeProjectLeaderRoleView implements ViewInterface {

    private ProjectViewModel project;

    public TakeProjectLeaderRoleView(ProjectViewModel project) {
        this.project = project;
    }

    public void show() {

        Header.showHeader("Påtag projektleder rolle på projekt " + project.projectNumber, 1);

        boolean confirm = Input.confirm();

        if (confirm) {
            try {
                TaskFusionCLI.projectFacade().takeProjectLeaderRole(project.projectNumber);
                Text.showSuccess("Du er nu projektleder på projekt " + project.projectNumber);
            } catch (Exception e) {
                Text.showExceptionError(e);
                return;
            }
        }

        return;

    }
}
