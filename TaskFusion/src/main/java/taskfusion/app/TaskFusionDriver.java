package taskfusion.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import taskfusion.domain.Project;

public class TaskFusionDriver {
    
    public static void main(String[] args) {
        TaskFusion taskFusion = new TaskFusion();

        try {
            // taskFusion.registerEmployee("John", "Dillermand");
            // taskFusion.login("jodi");
            
            // taskFusion.createProject("Projektet");

            // Project p = taskFusion.findProjectByProjectNumber("23001");

            // assertNotNull(p);
            // assertEquals("Projektet", p.getProjectTitle());
            // taskFusion.createProjectActivity("23001", "testaktivitet", 01, 02);
            // taskFusion.createProjectActivity("23001", "testaktivitet", 01, 02);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
