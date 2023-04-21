package taskfusion.cli.views;

import java.util.Set;

import taskfusion.cli.TaskFusionCLI;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ProjectActivityViewModel;
import taskfusion.viewModels.ReportViewModel;
import taskfusion.viewModels.WorktimeRegistrationViewModel;

public class ProjectReportView implements ViewInterface {

    private ReportViewModel report;
    private String[] exitMenu = {
            "Afslut rapportvisning",
            "Gem og afslut rapportvisning"
    };

    public ProjectReportView(ReportViewModel report) {
        this.report = report;
    }

    @Override
    public void show() {
        System.out.println("#" + "-".repeat(53) + "#");
        printHeader();
        System.out.println("#" + "-".repeat(53) + "#");
        System.out.println("");
        System.out.println("#" + "-".repeat(53) + "#");
        printEmployees();
        System.out.println("#" + "-".repeat(53) + "#");
        System.out.println("");
        System.out.println("#" + "-".repeat(53) + "#");
        printActivities();
        System.out.println("#" + "-".repeat(53) + "#");
        while (true) {
            int selectedMenuItem = Menu.showMenu(exitMenu, "Afslut visning eller gem rapport og afslut visning");
            switch (selectedMenuItem) {
                case 1:
                    return;
                case 2:
                    saveReport();
                    Text.showSuccess("Rapport gemt");
                    return;
                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

    private void printHeader() {
        System.out.printf("| %-50s  |%n", "Projektnavn: " + report.title);
        System.out.println("+" + "-".repeat(53) + "+");
        System.out.printf("| %-25s %25s |%n", "Løbenr.: " + report.projectNumber, report.reportDate);
        System.out.printf("| %-25s %25s |%n", "Startuge: " + report.startWeek, "Leder: " + report.projectLeader);
        System.out.printf("| %-25s %25s |%n", "Slutuge: " + report.endWeeek, "Kunde: " + report.customer);
    }

    private void printEmployees() {
        System.out.printf("| %-50S  |%n", "Projektmedarbejdere");
        System.out.println("+" + "-".repeat(53) + "+");
        Set<String> employees = report.employees.keySet();
        for (String employee : employees) {
            System.out.printf("| %-25s %25s |%n", report.employees.get(employee).fullName,
                    report.employees.get(employee).initials);
        }
    }

    private void printActivities() {
        System.out.printf("| %-50S  |%n", "Projektaktiviteter");
        System.out.println("+" + "-".repeat(53) + "+");
        for (ProjectActivityViewModel activity : report.activities) {
            System.out.printf("| %-25s %25s |%n", "Aktivitet: " + activity.title,
                    "Budgeteret tid: " + activity.timeBudget);
            System.out.printf("| %-25s %25s |%n", "Startuge: " + activity.startWeek, "Slutuge: " + activity.endWeek);
            for (WorktimeRegistrationViewModel worktimeRegistration : activity.worktimeRegistrations) {
                System.out.printf("| %-15s %-15s %19s |%n", worktimeRegistration.dateString,
                        worktimeRegistration.initials, worktimeRegistration.time + " time(r)");
            }
            System.out.printf("| %-50S  |%n", "Status");
            int progress = (int) Math.round((activity.totalWorktime / activity.timeBudget) * 49);
            System.out.printf("| [%-49s] |%n", "=".repeat(progress));
            System.out.println("|" + "-".repeat(53) + "|");
        }
        System.out.printf("| %-50s  |%n", "RAPPORT SLUT");
    }

    private void saveReport() {
        try {
            TaskFusionCLI.projectFacade().saveReport(report.projectNumber, report.reportDate);
        } catch (Exception e) {
            Text.showExceptionError(e);
        }
    }
}
