package taskfusion.cli.views;

import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.persistency.EmployeeRepository;

public class RegisterEmployeeView implements ViewInterface {
    
    public void show() {

        Header.showHeader("Opret medarbejder", 1);

        while(true) {
            
            String firstName = Input.lineWithCancel("Indtast fornavn");
            
            if(firstName == null) {
                return;
            }

            String lastName = Input.lineWithCancel("Indtast efternavn");
            
            if(lastName == null) {
                return;
            }

            try {
                EmployeeRepository.getInstance().registerEmployee(firstName, lastName);
            } catch (Exception e) {
                Text.showError(e);
                Text.showInstruction("Prøv igen");
                continue;
            }

            Text.showSuccess("Medarbejder oprettet.");
            return;
        }
    }
}
