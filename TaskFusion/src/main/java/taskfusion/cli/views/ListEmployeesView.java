package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

import taskfusion.cli.components.Header;
import taskfusion.cli.components.Input;
import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.viewModels.EmployeeViewModel;

public class ListEmployeesView implements ViewInterface {
    
    private List<EmployeeViewModel> employees;

    public ListEmployeesView(List<EmployeeViewModel> employees) {
        this.employees = employees;
    }

    public void show() {


        if(employees.size() == 0) {
            Text.showInfo("Ingen medarbejdere at vise");
            Input.enterToContinue("Tryk på Enter for at gå tilbage");
            return;
        }

        //Get titles
        List<String> optionsTextList = new ArrayList<String>();
        List<String> optionsKeyList = new ArrayList<String>();

        for (EmployeeViewModel employee : employees) {
            optionsTextList.add(employee.initials + ": " + employee.fullName);
            optionsKeyList.add(employee.initials);
        }

        Header.showHeader("Medarbejdere", 1);
        taskfusion.cli.components.List.showMapList(optionsKeyList, optionsTextList);
        Input.enterToContinue("Tryk på Enter for at gå tilbage");

        return;
    }

}
