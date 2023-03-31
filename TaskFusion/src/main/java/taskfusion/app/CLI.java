package taskfusion.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private boolean running;
    private String[] mainMenu = { "Hovedmenu", "Login", "Opret medarbejder", "Afslut" };
    private String[] employeeMenu = { "Medarbejder menu", "Opret projekt", "Se alle projekter", "Se egne projekter",
            "Opret fast aktivitet", "Se alle faste aktiviteter", "Log ud" };
    private Map<String, String[]> menus = new HashMap<>();

    public CLI() {
        running = true;
        System.out.println("|--------------------------|");
        System.out.println("| Velkommen til TaskFusion |");
        System.out.println("|--------------------------|");
        System.out.println();
    }

    public void run() {
        while (running) {
            String[] options = mainMenu;
            printMenu(options);

            Scanner scanner = new Scanner(System.in);
            int option;
        }
    }

    public static void printMenu(String[] menu) {
        System.out.println(" " + menu[0]);
        for (int i = 1; i < menu.length; i++) {
            System.out.print(i + " ");
            System.out.println(menu[i]);
        }
        System.out.println();
        System.out.println("Vælg menupunkt: ");
    }
}
