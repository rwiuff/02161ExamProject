package taskfusion.persistency;

import java.util.HashMap;
import java.util.Map;

import taskfusion.domain.Employee;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;

/**
 * Singleton instance, getInstance and resetInstance provided by ChatGPT v4
 */
public class EmployeeRepository {

    private Map<String, Employee> employees = new HashMap<>();

    /**
     * SINGLETON related
     */

    // Declare a private static instance of the class
    private static EmployeeRepository instance;

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

    /**
     * REPOSITORY related
     */

    public void create(String firstName, String lastName)
            throws InvalidPropertyException, ExhaustedOptionsException {
        
        Employee employee = new Employee(firstName, lastName);
        String initials = employee.getInitials();

        employees.put(initials, employee);
    }

    public Employee findByInitials(String initials) {
        String formattedInitials = initials.toLowerCase();
        return employees.get(formattedInitials);
    }

    public Map<String, Employee> all() {
        return employees;
    }

}
