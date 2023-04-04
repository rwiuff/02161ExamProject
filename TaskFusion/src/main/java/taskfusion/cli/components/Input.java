package taskfusion.cli.components;

import java.util.Scanner;

import taskfusion.cli.TaskFusionCLI;

public class Input {

    /**
     * Ask for a line input, returns the line as a string. If user wants to cancel, returns null.
     */
    public static String lineWithCancel(String prompt) {
        Text.showInstruction("Fortryd og for at gå tilbage, indtaste \"fortryd\"");
        Text.showInputPrompt(prompt);

        Scanner scanner = TaskFusionCLI.scanner();
  
        scanner.skip("\\s*");

        String line = TaskFusionCLI.scanner().nextLine();

        if (line.toLowerCase().equals("fortryd")) {
            return null;
        }

        return line;
    }


}
