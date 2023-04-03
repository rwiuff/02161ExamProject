package taskfusion.persistency;

import java.util.HashMap;
import java.util.Map;

import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;

public class EmployeeRepository {

    // Declare a private static instance of the class
    private static EmployeeRepository instance;

    public Map<String, Employee> employees = new HashMap<>();

    // Private constructor to prevent instantiation from other classes
    private EmployeeRepository() {
        
    }

    // Public method to get the Singleton instance
    public static EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
        }
        return instance;

    }

    public static void resetInstance() {
        instance = null;
    }

    public void registerEmployee(String firstName, String lastName)
            throws InvalidPropertyException, ExhaustedOptionsException {
        
        Employee employee = new Employee(firstName, lastName);
        String initials = employee.getInitials();

        employees.put(initials, employee);
    }

    public Employee findEmployee(String initials) {
        String formattedInitials = initials.toLowerCase();
        return employees.get(formattedInitials);
    }

}