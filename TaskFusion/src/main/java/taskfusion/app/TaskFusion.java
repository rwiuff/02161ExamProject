package taskfusion.app;

import java.util.HashMap;
import java.util.Map;

import taskfusion.domain.Employee;
import taskfusion.exceptions.AlreadyExistsException;
import taskfusion.exceptions.InvalidPropertyException;

public class TaskFusion {

    private Map<String, Employee> employees = new HashMap<>();

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
        return employees.get(initials);
    }

}