package taskfusion.cli.views;

import java.util.ArrayList;
import java.util.List;

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

        Text.showError("DENNE FEATURE ER IKKE FULDT IMPLEMENTERET.");

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


        String choice = Menu.showListOptions(optionsKeyList, optionsTextList, "Vælg medarbejder", "Medarbejdere");

        if(choice == null) {
            return;
        }

        Text.showError("MANGLER IMPLEMENTERING");

        return;
    }

}
