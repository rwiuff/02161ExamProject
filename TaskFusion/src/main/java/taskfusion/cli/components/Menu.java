package taskfusion.cli.components;

import java.util.Scanner;

public class Menu {
    
    public static int showMenu(String[] options, String header) {
        
        // Display a header, if a header is given
        if (header != null && !header.isEmpty()) {
            Header.showHeader(header, 11);
        }
        
        // Write menu options
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i+1, options[i]);
        }
        
        // Get a valid menu choice
        int choice = 0;
        int attempt = 0;
        Scanner scanner = new Scanner(System.in);
        
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

        //scanner.close();
        
        return choice;
    }

}
