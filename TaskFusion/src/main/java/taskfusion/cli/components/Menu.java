package taskfusion.cli.components;

import java.util.Arrays;
import java.util.Scanner;

import taskfusion.cli.TaskFusionCLI;

public class Menu {
    
    public static int showMenu(String[] options, String header) {
        
        // Display a header, if a header is given
        Header.showHeader(header, 11);
        
        // Write menu options
        List.showNumberedOptions(options);
        
        // Get a valid menu choice
        int choice = 0;
        int attempt = 0;
        Scanner scanner = TaskFusionCLI.scanner();
        
        while (choice < 1 || choice > options.length) {
            
            if (attempt > 0) {
                Text.showError("Ugyldigt valg");
            }
            
            Text.showInputPrompt("Vælg menupunkt");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // consume invalid input
            }
            attempt++;
        }
        
        return choice;
    }

    public static String showListOptions(String[] optionKeys, String[] optionTexts, String prompt, String header) {

        // Display a header, if a header is given
        Header.showHeader(header, 11);

        // Write menu options
        List.showMapList(optionKeys, optionTexts);
        
        // Get a valid menu choice
        int attempt = 0;
        Scanner scanner = TaskFusionCLI.scanner();

        Text.showInstruction("For at go tilbage, indtast \"tilbage\"");
        
        while (true) {
            
            Text.showInputPrompt(prompt);
            
            String choice = scanner.nextLine();

            if(choice.equals("tilbage")) {
                return null;
            }

            if(Arrays.asList(optionKeys).contains(choice)){
                return choice;
            }

            scanner.next();
            attempt++;
            Text.showError("Ugyldigt valg");
        }
 

    }

}
