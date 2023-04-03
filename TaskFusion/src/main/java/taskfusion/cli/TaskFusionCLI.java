package taskfusion.cli;

import java.io.IOException;
import java.util.Scanner;

import taskfusion.app.TaskFusion;
import taskfusion.cli.components.AsciiArt;
import taskfusion.cli.controllers.GuestMenuController;
import taskfusion.persistency.Seeder;

public class TaskFusionCLI  {

	private boolean seedDemoData = true;
    
	private static TaskFusionCLI instance;

    private TaskFusion taskFusion;
	private Scanner scanner;

	/**
	 * Application initialisation
	 */

    private TaskFusionCLI() {
		seedDemoData();
		
        taskFusion = new TaskFusion();
		scanner = new Scanner(System.in);
		
    }

    public static void main(String[] args) throws Exception {
		new TaskFusionCLI().mainLoop();
	}

    public void mainLoop() throws IOException {
		AsciiArt.showLogo();
		new GuestMenuController().showMenu();
		scanner.close();
	}

	/**
	 * Seeding
	 */
	private void seedDemoData() {
		if(seedDemoData) {
			Seeder seeder = new Seeder();
			seeder.seedDemoData();
		}
	}

	/**
	 * Singleton methods
	 */

    public static void resetInstance() {
        instance = null;
    }

	public static TaskFusionCLI getInstance() {
        if (instance == null) {
            instance = new TaskFusionCLI();
        }
        return instance;
    }

	/**
	 * Getters
	 */

	 public static TaskFusion taskFusion() {
		TaskFusionCLI taskFusionCLI = TaskFusionCLI.getInstance();
		return taskFusionCLI.taskFusion;
	 }

	 public static Scanner scanner() {
		TaskFusionCLI taskFusionCLI = TaskFusionCLI.getInstance();
		return taskFusionCLI.scanner;
	 }
}
