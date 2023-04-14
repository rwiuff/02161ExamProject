package taskfusion.viewModels;

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

}
