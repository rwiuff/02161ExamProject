package taskfusion.app;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

import taskfusion.domain.Employee;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;

public class TaskFusion {

    private Map<String, Employee> employees = new HashMap<>();
    private Employee loggedInUser;

    public static void main(String[] args) {

    }

    public void createEmployee(String firstName, String lastName) throws InvalidPropertyException, AlreadyExistsException {
        Employee e = new Employee(firstName, lastName);
        String initials = e.getInitials();

        if(findEmployee(initials) != null) {
            throw new AlreadyExistsException("Medarbejder ekisisterer allerede");
        }
        
        employees.put(initials, e);
    }

    public Employee findEmployee(String initials) {
        String formattedInitials = initials.toLowerCase();
        return employees.get(formattedInitials);
    }

    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    public void login(String initials) throws NotFoundException {
        loggedInUser = findEmployee(initials);

        if(loggedInUser == null) {
            throw new NotFoundException("Ukendt medarbejder");
        }
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public void logout() {
        loggedInUser = null;
    }

}