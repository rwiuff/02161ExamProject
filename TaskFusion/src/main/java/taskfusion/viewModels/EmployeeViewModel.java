package taskfusion.viewModels;

import java.util.ArrayList;
import java.util.List;

import taskfusion.domain.Employee;

public class EmployeeViewModel extends ViewModel {
  public String firstName;
  public String lastName;
  public String initials;
  public String fullName;

  public EmployeeViewModel(Employee employee) {
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
    this.initials = employee.getInitials();
    this.fullName = employee.getFullName();
  }

  public static List<EmployeeViewModel> listFromModels(List<Employee> employeeList) {
    List<EmployeeViewModel> list = new ArrayList<EmployeeViewModel>();

    for (Employee employee : employeeList) {
      list.add(employee.toViewModel());
    }

    return list;
  }
}
