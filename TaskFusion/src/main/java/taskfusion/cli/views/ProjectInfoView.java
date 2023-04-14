package taskfusion.cli.views;

import taskfusion.cli.components.Header;
import taskfusion.cli.components.Line;
import taskfusion.viewModels.ProjectViewModel;

public class ProjectInfoView implements ViewInterface {
    
    ProjectViewModel project;

    public ProjectInfoView(ProjectViewModel project) {
        this.project = project;
    }

    public void show() {

        Header.showHeader(project.projectTitle, 1);
        System.out.println("Projekt nummber: " + project.projectNumber);
        System.out.println("Projekt leder: " + (project.projectLeaderFullName == null ? "_____" : project.projectLeaderFullName) );
        System.out.println("Medarbejdere: " + project.assignedEmployeesAmount);

        Line.showLine(4);
    }
}
