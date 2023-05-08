package taskfusion.cli;

import java.io.IOException;
import java.util.Scanner;

import taskfusion.app.TaskFusion;
import taskfusion.cli.controllers.GuestMenuController;
import taskfusion.cli.views.WelcomeView;

public class TaskFusionCLI {

	private static TaskFusionCLI instance;

	private TaskFusion taskFusion;

	private Scanner scanner;

	/**
	 * Application initialisation
	 */

	private TaskFusionCLI() {

		taskFusion = new TaskFusion();
		scanner = new Scanner(System.in);

	}

	public static void main(String[] args) throws Exception {
		TaskFusionCLI.getInstance().mainLoop();
	}

	public void mainLoop() throws IOException {
		new WelcomeView().show();
		new GuestMenuController().showMenu();
		scanner.close();
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
