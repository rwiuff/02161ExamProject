package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;

public class LoginView implements ViewInterface {
    
    public void show() {

        Header.showHeader("Login", 1);

        while(true) {
            
            String initials = Input.lineWithCancel("Indtast dine initialer");
            
            if(initials == null) {
                return;
            }

            try {
                TaskFusionCLI.taskFusion().login(initials);
            } catch (Exception e) {
                Text.showError(e);
            }

            if(TaskFusionCLI.taskFusion().isLoggedIn()) {
                return;
            }
            
        }

    }

}
