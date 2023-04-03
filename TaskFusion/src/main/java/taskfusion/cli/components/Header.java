package taskfusion.cli.components;

public class Header {
    
    public static void showHeader(String header, int level) {
        
        if(level == 1) {
            System.out.println("===========================");
        }

        if(level == 2) {
            System.out.println("---------------------------");
        }

        if(level == 3) {
            System.out.println("- - - - - - - - - - - - - - ");
        }

        System.out.println(header);

        if(level == 1) {
            System.out.println("===========================");
        }

        if(level == 2) {
            System.out.println("---------------------------");
        }

        if(level == 3) {
            System.out.println("- - - - - - - - - - - - - - ");
        }

    }
}
