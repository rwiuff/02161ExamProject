package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.RegularActivityViewModel;

public class ListRegularActivitiesView implements ViewInterface {
    
    private List<RegularActivityViewModel> activities;

    public ListRegularActivitiesView(List<RegularActivityViewModel> activities) {
        this.activities = activities;
    }

    public void show() {

        if(activities.size() == 0) {
            Text.showInfo("Du har ingen faste aktiviteter");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }

        //Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        for (RegularActivityViewModel activity : activities) {
            optionsTextList.add(activity.title + ": " + activity.startWeek + " - " + activity.endWeek);
            optionsKeyList.add(""+activity.id);
        }

        String choice = Menu.showListOptions(optionsKeyList, optionsTextList, "Vælg aktivitet", "Dine faste aktiviteter");

        if(choice == null) {
            return;
        }

        RegularActivityViewModel activity;
        try {
            activity = TaskFusionCLI.employeeFacade().getRegularActivityById(Integer.parseInt(choice));
        } catch (Exception e) {
            Text.showExceptionError(e);
            return;
        }

        Text.showInfo(activity.title);
        
        Text.showError("MANGLER IMPLEMENTERING");

        return;
    }

}
