package taskfusion.ui.controllers;

import java.util.Scanner;

public class NavigationController {
    


    public static int displayMenu(String[] options, String header) {
        
        // Display a header, if a header is given
        if (header != null && !header.isEmpty()) {
            System.out.println(header.toUpperCase());
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
                System.out.println("!!! Ugyldigt valg");
            }
            
            System.out.print("\nVælg menupunkt: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // consume invalid input
            }
            attempt++;
        }
        
        return choice;
    }


}
