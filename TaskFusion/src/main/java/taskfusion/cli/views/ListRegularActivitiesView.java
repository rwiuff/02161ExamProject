package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.components.Input;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.RegularActivityViewModel;

public class ListRegularActivitiesView implements ViewInterface {
    
    private List<RegularActivityViewModel> activities;

    public ListRegularActivitiesView(List<RegularActivityViewModel> activities) {
        this.activities = activities;
    }

    public void show() {

        Text.showError("DENNE FEATURE ER IKKE FULDT IMPLEMENTERET.");

        if(activities.size() == 0) {
            Text.showInfo("Du har ingen faste aktiviteter");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }

        //Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        Integer i = 0;

        for (RegularActivityViewModel activity : activities) {
            i += 1;
            optionsTextList.add(activity.title + ": " + activity.startWeek + " - " + activity.endWeek);
            optionsKeyList.add(""+i);
        }

        String[] optionTexts = optionsTextList.toArray(new String[0]);
        String[] optionKeys = optionsKeyList.toArray(new String[0]);

        String choice = Menu.showListOptions(optionKeys, optionTexts, "Vælg aktivitet", "Dine faste aktiviteter");

        if(choice == null) {
            return;
        }

        Text.showError("MANGLER IMPLEMENTERING");

        return;
    }

}
