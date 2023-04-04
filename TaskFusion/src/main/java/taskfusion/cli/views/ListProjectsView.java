package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.domain.Project;
import taskfusion.exceptions.NotFoundException;

public class ListProjectsView implements ViewInterface {
    

    public void show() {

    }

    public Project select() throws NotFoundException {

        Map<String, Project> projects =  TaskFusionCLI.taskFusion().getLoggedInUser().getProjects();

        //Get the options
        String[] optionKeys = projects.keySet().toArray(new String[0]);

        //Get titles
        List<String> optionsList = new ArrayList<String>();

        for (Project project : projects.values()) {
            optionsList.add(project.getProjectTitle());
        }

        String[] optionTexts = optionsList.toArray(new String[0]);

        String choice = Menu.showListOptions(optionKeys, optionTexts, "Vælg projekt", "Dine projekter");

        if(choice == null) {
            return null;
        }

        return TaskFusionCLI.taskFusion().findProject(choice);

    }

}
