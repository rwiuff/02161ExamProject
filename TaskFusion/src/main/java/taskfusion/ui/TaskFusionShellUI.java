package taskfusion.ui;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import taskfusion.TaskFusion;

public class TaskFusionShellUI  {
    
    TaskFusion taskFusion;

    public TaskFusionShellUI() {
        taskFusion = new TaskFusion();
    }

    public static void main(String[] args) throws Exception {
		
		new TaskFusionShellUI().mainLoop(System.in, System.out);
	}


    public void mainLoop(InputStream in, PrintStream out) throws IOException {
		BufferedReader rs = new BufferedReader(new InputStreamReader(in));
		String choice = null;
		do {
			showMenu(out);
			choice = rs.readLine();
			if (choice == null) {
				break;
			}
			int number = Integer.valueOf(choice);
			if (number == 0) {
				break;
			}
			processChoice(number, out);

		} while (choice != null);
	}

    private void showMenu(PrintStream out) {
		out.println("   1) Login");
		out.println("   2) Register Employee");
		out.println("   0) exit");
		out.println("Select a menuitem: ");
	}

    private void processChoice(int number, PrintStream out) {
		switch (number) {
		case 1:
			out.println("LOGIN!");
			break;
		case 2:
            out.println("REGSITER EMPLOYEE!!");
			break;
		default:
			break;
		}
	}

}
