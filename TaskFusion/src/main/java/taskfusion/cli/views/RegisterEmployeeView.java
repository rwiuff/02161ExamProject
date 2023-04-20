package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;

public class RegisterEmployeeView implements ViewInterface {

    public void show() {

        Header.showHeader("Opret medarbejder", 1);

        while (true) {

            String firstName = Input.lineWithCancel("Indtast fornavn");

            if (firstName == null) {
                return;
            }

            String lastName = Input.lineWithCancel("Indtast efternavn");

            if (lastName == null) {
                return;
            }

            try {
                TaskFusionCLI.employeeFacade().registerEmployee(firstName, lastName);
            } catch (Exception e) {
                Text.showExceptionError(e);
                Text.showInfo("Prøv igen");
                continue;
            }

            Text.showSuccess("Medarbejder oprettet.");
            return;
        }
    }
}
