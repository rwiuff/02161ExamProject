package taskfusion.facades;

import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.viewModels.EmployeeViewModel;

public interface EmployeeFacadeInterface {

    public void registerEmployee(String firstName, String lastName) throws InvalidPropertyException, ExhaustedOptionsException;
    public EmployeeViewModel findEmployeeByInitials(String initials) throws NotFoundException;

}