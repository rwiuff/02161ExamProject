package taskfusion.cli.components;

public class List {
    


    public static void showMapList(String[] optionKeys, String[] optionTexts) {
        for (int i = 0; i < optionKeys.length; i++) {
            System.out.printf("%s. %s%n", optionKeys[i], optionTexts[i]);
        }
    }

    public static void showNumberedOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i+1, options[i]);
        }
    }
}
