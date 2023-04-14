package taskfusion.helpers;

import java.util.List;
import java.util.Map;

import taskfusion.domain.Employee;
import taskfusion.domain.Project;
import taskfusion.domain.WorktimeRegistration;

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

    public static void printWorktimeRegistrations(List<WorktimeRegistration> list) {
        for (WorktimeRegistration item : list) {
            System.out.println(item.getId() + ":" + item.getTime());
        }
    }
}
