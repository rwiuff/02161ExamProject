package taskfusion.cli;

import java.io.IOException;
import java.util.Scanner;

import taskfusion.app.TaskFusion;
import taskfusion.cli.components.Text;
import taskfusion.cli.controllers.GuestMenuController;
import taskfusion.cli.views.WelcomeView;
import taskfusion.persistency.Seeder;

public class TaskFusionCLI {

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
		TaskFusionCLI.getInstance().mainLoop();
	}

	public void mainLoop() throws IOException {
		new WelcomeView().show();
		new GuestMenuController().showMenu();
		scanner.close();
	}

	/**
	 * Seeding
	 */
	private void seedDemoData() {
		if (seedDemoData) {
			Seeder seeder = new Seeder();

			try {
				seeder.seedDemoData();
			} catch (Exception e) {
				Text.showExceptionError(e);
				e.printStackTrace();
			}
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
