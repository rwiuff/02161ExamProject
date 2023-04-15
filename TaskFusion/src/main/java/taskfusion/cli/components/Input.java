package taskfusion.cli.components;

import java.io.IOException;
import java.util.Scanner;

import taskfusion.cli.TaskFusionCLI;

public class Input {

    /**
     * Ask for a line input, returns the line as a string. If user wants to cancel, returns null.
     */
    public static String lineWithCancel(String prompt) {
        Text.showInstruction("Fortryd og for at gå tilbage, indtaste \"fortryd\"");
        

        Scanner scanner = TaskFusionCLI.scanner();
        
        Text.showInputPrompt(prompt);
        scanner.skip("\\s*");

        String line = TaskFusionCLI.scanner().nextLine();
        System.out.print(line);
        if (line.toLowerCase().contains("fortryd")) {
            return null;
        }

        return line;
    }

    public static void enterToContinue(String prompt) {
        Text.lineBreak();
        Text.showInfo(prompt);
        try {
            System.in.read();
        } catch (IOException e) {

        }
    }

}
