package taskfusion.cli.components;

import java.util.Arrays;
import java.util.Scanner;

import taskfusion.cli.TaskFusionCLI;

public class Menu {

    public static int showMenu(String[] options, String header) {

        // Display a header, if a header is given
        Header.showHeader(header, 2);

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
            scanner.skip("\\s*");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // consume invalid input
            }
            attempt++;
        }

        return choice;
    }

    public static int showMenu(java.util.List<String> options, String header) {
        String[] optionTexts = options.toArray(new String[0]);
        return Menu.showMenu(optionTexts, header);
    }

    public static String showListOptions(String[] optionKeys, String[] optionTexts, String prompt, String header) {

        // Display a header, if a header is given
        Header.showHeader(header, 2);
        Text.showInstruction("For at go tilbage, indtast \"tilbage\"");

        // Write menu options
        List.showMapList(optionKeys, optionTexts);

        Scanner scanner = TaskFusionCLI.scanner();

        while (true) {

            Text.showInputPrompt(prompt);
            scanner.skip("\\s*");

            String choice = scanner.nextLine();

            if (choice.toLowerCase().contains("tilbage")) {
                return null;
            }

            if (Arrays.asList(optionKeys).contains(choice)) {
                return choice;
            }

            Text.showError("Ugyldigt valg");
        }

    }

    public static String showListOptions(java.util.List<String> optionsKeyList, java.util.List<String> optionsTextList,
            String prompt, String header) {

        String[] optionTexts = optionsTextList.toArray(new String[0]);
        String[] optionKeys = optionsKeyList.toArray(new String[0]);

        return Menu.showListOptions(optionKeys, optionTexts, prompt, header);
    }

}
