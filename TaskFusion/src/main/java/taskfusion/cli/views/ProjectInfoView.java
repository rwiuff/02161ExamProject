package taskfusion.cli.views;

import taskfusion.cli.components.Header;
import taskfusion.cli.components.Line;
import taskfusion.domain.Project;

public class ProjectInfoView implements ViewInterface {
    
    Project project;

    public ProjectInfoView(Project project) {
        this.project = project;
    }

    public void show() {

        Header.showHeader(project.getProjectTitle(), 1);
        System.out.println("Projekt nummber: " + project.getProjectNumber());
        System.out.println("Projekt leder: " + (project.getProjectLeader() == null ? "_____" : project.getProjectLeader().getFullName()) );
        System.out.println("Medarbejdere: " + project.getAssignedEmployees().size());

        Line.showLine(4);
    }
}
