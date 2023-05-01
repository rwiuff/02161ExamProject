package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.controllers.ProjectMenuController;
import taskfusion.exceptions.NotFoundException;
import taskfusion.viewModels.ProjectViewModel;

public class ListProjectsView implements ViewInterface {
    private List<ProjectViewModel> projects;

    public ListProjectsView(List<ProjectViewModel> projects) {
        this.projects = projects;
    }

    public void show() {

        // Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        for (ProjectViewModel project : projects) {
            optionsTextList.add(project.projectTitle);
            optionsKeyList.add(project.projectNumber);
        }

        while (true) {
            String choice = Menu.showListOptions(optionsKeyList, optionsTextList, "Vælg projekt", "Dine projekter");

            if (choice == null) {
                return;
            }

            ProjectViewModel project = null;
            try {
                project = TaskFusionCLI.taskFusion().findProjectByProjectNumber(choice);
            } catch (NotFoundException e) {
                Text.showExceptionError(e);
            }

            new ProjectMenuController(project).showMenu();

        }

    }

}
