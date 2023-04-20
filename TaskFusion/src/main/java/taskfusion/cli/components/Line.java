package taskfusion.cli.components;

public class Line {

    public static void showLine(int level) {
        if (level == 1) {
            System.out.println("###########################");
        }

        if (level == 2) {
            System.out.println("===========================");
        }

        if (level == 3) {
            System.out.println("---------------------------");
        }

        if (level == 4) {
            System.out.println("- - - - - - - - - - - - - - ");
        }
    }
}
