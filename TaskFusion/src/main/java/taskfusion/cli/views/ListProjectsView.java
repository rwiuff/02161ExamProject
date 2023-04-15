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
    

    public void show() {
        List<ProjectViewModel> projects = TaskFusionCLI.projectFacade().getUserProjects();

        //Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        for (ProjectViewModel project : projects) {
            optionsTextList.add(project.projectTitle);
            optionsKeyList.add(project.projectNumber);
        }

        String[] optionTexts = optionsTextList.toArray(new String[0]);
        String[] optionKeys = optionsKeyList.toArray(new String[0]);

        while(true) {
            String choice = Menu.showListOptions(optionKeys, optionTexts, "Vælg projekt", "Dine projekter");

            if(choice == null) {
                return;
            }

            ProjectViewModel project = null;
            try {
                project = TaskFusionCLI.projectFacade().findProjectByProjectNumber(choice);
            } catch (NotFoundException e) {
                Text.showExceptionError(e);
            }

            new ProjectMenuController(project).showMenu();

        }

    }



}
