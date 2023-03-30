package taskfusion;

import java.util.HashMap;
import java.util.Map;

public class TaskFusion {

    private Map<String, Employee> employees = new HashMap<>();

    public static void main(String[] args) {

    }

    public void createEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        String initials = e.getInitials();
        employees.put(initials, e);
    }

    public Employee findEmployee(String initials) {
        return employees.get(initials);
    }
}