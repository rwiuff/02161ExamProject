package taskfusion.helpers;

import java.util.Map;

import taskfusion.domain.Employee;
import taskfusion.domain.Project;

public class PrintHelper {
    

    public static void printEmployees(Map<String, Employee> map) {
        for (Map.Entry<String, Employee> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().getFullName());
        }
    }

    public static void printProjects(Map<String, Project> map) {
        for (Map.Entry<String, Project> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().getProjectTitle());
        }
    }
}
