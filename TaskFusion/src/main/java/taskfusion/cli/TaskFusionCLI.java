package taskfusion.cli;

import java.io.IOException;


import taskfusion.app.TaskFusion;
import taskfusion.cli.components.AsciiArt;
import taskfusion.cli.controllers.GuestMenuController;

public class TaskFusionCLI  {
    
    public TaskFusion taskFusion;
	public GuestMenuController guestMenuController;

    public TaskFusionCLI() {
        taskFusion = new TaskFusion();
		guestMenuController = new GuestMenuController(taskFusion);
    }

    public static void main(String[] args) throws Exception {
		new TaskFusionCLI().mainLoop();
	}

    public void mainLoop() throws IOException {
		AsciiArt.showLogo();
		guestMenuController.showMenu();
	}

}
