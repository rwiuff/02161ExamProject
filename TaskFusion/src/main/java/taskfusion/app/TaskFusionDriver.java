package taskfusion.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Map;

import taskfusion.domain.Project;

public class TaskFusionDriver {
    
    public static void main(String[] args) {
        TaskFusion taskFusion = new TaskFusion();

        try {
            taskFusion.registerEmployee("John", "Dillermand");
            taskFusion.login("jodi");
            
            taskFusion.createProject("Projektet");

            //ProjectRepository projectRepo = ProjectRepository.getInstance();
            //Project p = projectRepo.findProjectByProjectNumber(23001);
            Project p = taskFusion.projectRepo.findByProjectNumber("23001");

            assertNotNull(p);
            assertEquals("Projektet", p.getProjectTitle());
            taskFusion.createProjectActivity("23001", "testaktivitet", 2303, 2304);
            assertNotNull(taskFusion.getLoggedInUser().findProject("23001"));
            assertNotNull(taskFusion.findProjectByProjectNumber("23001"));
            assertNotNull(taskFusion.getLoggedInUser().findProject("23001").findProjectActivity("testaktivitet"));
            // WHAT THE FUCK
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

}
