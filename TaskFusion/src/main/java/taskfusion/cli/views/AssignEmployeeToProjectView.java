package taskfusion.cli.views;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.EmployeeViewModel;
import taskfusion.viewModels.ProjectViewModel;

public class AssignEmployeeToProjectView implements ViewInterface {

    private ProjectViewModel project;

    public AssignEmployeeToProjectView(ProjectViewModel project) {
        this.project = project;
    }

    public void show() {

        Header.showHeader("Tilføj medarbejder til projekt " + project.projectNumber, 1);

        while (true) {

            String initials = Input.lineWithCancel("Medarbejder initialer");

            if (initials == null) {
                return;
            }

            EmployeeViewModel employee = null;

            try {
                employee = TaskFusionCLI.employeeFacade().findEmployeeByInitials(initials);
                TaskFusionCLI.projectFacade().assignEmployeeToProject(project.projectNumber, employee.initials);
            } catch (Exception e) {
                Text.showExceptionError(e);
                Text.showInfo("Prøv igen");
                continue;
            }

            Text.showSuccess(employee.fullName + " tilføjet til projekt " + project.projectNumber);
            return;
        }
    }
}
