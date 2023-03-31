package taskfusion.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import taskfusion.domain.Project;

public class TaskFusionDriver {
    
    public static void main(String[] args) {
        TaskFusion taskFusion = new TaskFusion();

        try {
            taskFusion.registerEmployee("John", "Dillermand");
            taskFusion.login("jodi");
            
            taskFusion.createProject("Projektet");

            //ProjectRepository projectRepo = ProjectRepository.getInstance();
            //Project p = projectRepo.findProject(23001);
            Project p = taskFusion.projectRepo.getProject("23001");

            assertNotNull(p);
            assertEquals("Projektet",p.getProjectTitle());

            System.out.println("STOP");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

}
