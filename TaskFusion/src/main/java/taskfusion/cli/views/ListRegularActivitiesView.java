package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.RegularActivityViewModel;

public class ListRegularActivitiesView implements ViewInterface {

    private List<RegularActivityViewModel> activities;

    public ListRegularActivitiesView(List<RegularActivityViewModel> activities) {
        this.activities = activities;
    }

    public void show() {

        if (activities.size() == 0) {
            Text.showInfo("Du har ingen faste aktiviteter");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }

        // Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        for (RegularActivityViewModel activity : activities) {
            optionsTextList.add(activity.startWeek + " - " + activity.endWeek);
            optionsKeyList.add(activity.title);
        }

        Header.showHeader("Faste aktiviteter", 1);
        taskfusion.cli.components.List.showMapList(optionsKeyList, optionsTextList);
        Input.enterToContinue("Tryk på Enter for at gå tilbage");

        return;
    }

}
