package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;

public class CreateRegularActivityView implements ViewInterface {
    
    public void show() {

        Header.showHeader("Opret fast aktivitet", 1);

        while(true) {
            
            String title = Input.lineWithCancel("Indtast titel");
            
            if(title == null) {
                return;
            }
            Text.lineBreak();
            Text.showInfo("Format: ugenummer med 2 tal, efterfulgt af sidste 2 tal af årstal. ex: 0123 for uge 1 i 2023.");
            Text.showInfo("Startuge starter fra mandag i den angivne uge.");
            String startWeek = Input.lineWithCancel("Indtast startuge [UUYY]");
            
            if(startWeek == null) {
                return;
            }
            Text.lineBreak();
            Text.showInfo("Format: ugenummer med 2 tal, efterfulgt af sidste 2 tal af årstal. ex: 0123 for uge 1 i 2023.");
            Text.showInfo("Slutuge ender ved fredagen i den angivne uge.");
            String endWeek = Input.lineWithCancel("Indtast slutuge [UUYY]");
            
            if(endWeek == null) {
                return;
            }

            try {
                TaskFusionCLI.employeeFacade().createRegularActivity(title, Integer.parseInt(startWeek), Integer.parseInt(endWeek));
            } catch (Exception e) {
                Text.showExceptionError(e);
                Text.showInfo("Prøv igen");
                continue;
            }

            Text.showSuccess(title + " oprettet.");
            return;
        }
    }
}
