package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import taskfusion.cli.components.Input;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.ReportViewModel;

public class ListProjectReportView implements ViewInterface {

    private Map<String, ReportViewModel> reports;

    public ListProjectReportView(Map<String, ReportViewModel> reports) {
        this.reports = reports;
    }

    public void show() {

        if (reports.size() == 0) {
            Text.showInfo("Ingen rapporter at vise");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }
        // Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        int i = 0;

        Set<String> reportOptions = this.reports.keySet();
        for (String reportDate : reportOptions) {
            i++;
            optionsTextList.add(reportDate);
            optionsKeyList.add("" + i);
        }

        String choice = Menu.showListOptions(optionsKeyList, optionsTextList, "Vælg dato", "Projekt rapporter");

        if (choice == null) {
            return;
        }

        ReportViewModel report = reports.get(optionsTextList.get(i - 1));
        new ProjectRaportView(report).show();
        return;
    }

}
