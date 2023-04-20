package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.components.Formatter;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class ListWorktimeRegistrationsView implements ViewInterface {

    private List<WorktimeRegistrationViewModel> worktimeRegistrations;

    public ListWorktimeRegistrationsView(List<WorktimeRegistrationViewModel> worktimeRegistrations) {
        this.worktimeRegistrations = worktimeRegistrations;
    }

    public void show() {

        if (worktimeRegistrations.size() == 0) {
            Text.showInfo("Ingen tidsregistreringer at vise");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }

        // Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        for (WorktimeRegistrationViewModel worktimeRegistration : worktimeRegistrations) {
            optionsTextList.add(Formatter.dateToString(worktimeRegistration.date) + " : " + worktimeRegistration.time
                    + "(" + worktimeRegistration.initials + ")");
            optionsKeyList.add("" + worktimeRegistration.id);
        }

        Header.showHeader("Tidsregistreringer", 1);
        taskfusion.cli.components.List.showMapList(optionsKeyList, optionsTextList);
        Input.enterToContinue("Tryk på Enter for at gå tilbage");

        return;
    }

}
