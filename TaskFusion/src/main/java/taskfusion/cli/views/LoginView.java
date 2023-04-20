package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.cli.controllers.EmployeeMenuController;

public class LoginView implements ViewInterface {

    public void show() {

        Header.showHeader("Login", 1);

        while (true) {

            if (TaskFusionCLI.taskFusion().isLoggedIn()) {
                new EmployeeMenuController().showMenu();
                return;
            }

            String initials = Input.lineWithCancel("Indtast dine initialer");

            if (initials == null) {
                return;
            }

            try {
                TaskFusionCLI.taskFusion().login(initials);
            } catch (Exception e) {
                Text.showExceptionError(e);
            }

        }

    }

}
