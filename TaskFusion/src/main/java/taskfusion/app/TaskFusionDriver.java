package taskfusion.app;

public class TaskFusionDriver {
    
    public static void main(String[] args) {
        TaskFusion taskFusion = new TaskFusion();
        try {
            taskFusion.getEmployeeFacade().registerEmployee("Rasmus", "Wiuff");
            taskFusion.login("rawi");
            taskFusion.getProjectFacade().createProject("Web");
            taskFusion.getProjectFacade().takeProjectLeaderRole("23001");
            taskFusion.getProjectFacade().getTotalWorktimeRegistrationsForProject("23001");
            System.out.println("here");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
