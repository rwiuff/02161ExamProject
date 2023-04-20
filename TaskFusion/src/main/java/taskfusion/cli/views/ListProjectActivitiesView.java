package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.components.Input;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.controllers.ProjectActivityMenuController;
import taskfusion.viewModels.ProjectActivityViewModel;
import taskfusion.viewModels.ProjectViewModel;

public class ListProjectActivitiesView implements ViewInterface {

    private List<ProjectActivityViewModel> activities;
    private ProjectViewModel project;

    public ListProjectActivitiesView(List<ProjectActivityViewModel> activities, ProjectViewModel project) {
        this.activities = activities;
        this.project = project;
    }

    public void show() {

        if (activities.size() == 0) {
            Text.showInfo("Ingen aktiviteter at vise");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }

        // Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        int i = 0;

        for (ProjectActivityViewModel activity : activities) {
            i += 1;
            optionsTextList.add(activity.title + ": " + activity.startWeek + " - " + activity.endWeek);
            optionsKeyList.add("" + i);
        }

        String choice = Menu.showListOptions(optionsKeyList, optionsTextList, "Vælg aktivitet", "projekt aktiviteter");

        if (choice == null) {
            return;
        }

        ProjectActivityViewModel activity = activities.get(i - 1);

        new ProjectActivityMenuController(project, activity).showMenu();

        return;
    }

}
