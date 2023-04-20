package taskfusion.persistency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import taskfusion.domain.Employee;
import taskfusion.domain.RegularActivity;
import taskfusion.exceptions.ExhaustedOptionsException;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;

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

    public RegularActivity findRegularActivityById(int id) throws NotFoundException {

        List<RegularActivity> list = allRegularActivities();

        for (RegularActivity regularActivity : list) {
            if (regularActivity.getId() == id) {
                return regularActivity;
            }
        }

        throw new NotFoundException("Kunne ikke finde fast aktivitet");

    }

    // public int generateRegularActivityId() {
    // Integer lastId = 0;

    // List<RegularActivity> list = allRegularActivities();

    // for(RegularActivity regularActivity : list) {
    // Integer id = regularActivity.getId();
    // if(id > lastId) {
    // lastId = id;
    // }
    // }

    // return lastId + 1;
    // }

    public int generateRegularActivityId() {
        return allRegularActivities().size() + 1;
    }

    private List<RegularActivity> allRegularActivities() {
        List<RegularActivity> list = new ArrayList<>();

        for (Entry<String, Employee> entry : employees.entrySet()) {

            Employee employee = entry.getValue();

            list.addAll(employee.getRegularActivities());
        }
        return list;
    }

}
