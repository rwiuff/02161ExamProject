package taskfusion.app;

import taskfusion.exceptions.NotFoundException;
import taskfusion.viewModels.EmployeeViewModel;

interface AuthentificationInterface {
    public EmployeeViewModel getLoggedInUser();
    public void login(String initials) throws NotFoundException;
    public boolean isLoggedIn();
    public void logout();
}
