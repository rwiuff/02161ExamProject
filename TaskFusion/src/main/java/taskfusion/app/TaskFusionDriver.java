package taskfusion.app;

import taskfusion.domain.Project;
import taskfusion.persistency.ProjectRepository;

public class TaskFusionDriver {
    
    public static void main(String[] args) {
        TaskFusion taskFusion = new TaskFusion();

        try {
            taskFusion.registerEmployee("John", "Dillermand");
            taskFusion.login("jodi");
            
            taskFusion.createProject("Projektet");

            //ProjectRepository projectRepo = ProjectRepository.getInstance();
            //Project p = projectRepo.findProject(23001);
            Project p = taskFusion.projectRepo.findProject(23001);

            System.out.println("STOP");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

}
