package taskfusion.cli.views;

import io.cucumber.java.bs.I;
import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Input;

public class LoginView implements ViewInterface {
    
    public void show() {

        while(true) {
            
            String initials = Input.lineWithCancel("Indtast dine initialer");
            
            if(initials == null) {
                break;
            }

            try {
                TaskFusionCLI.taskFusion().login(initials);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(TaskFusionCLI.taskFusion().isLoggedIn()) {
                break;
            }
            
        }

    }

}
