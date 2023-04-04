package taskfusion.cli.components;

public class Header {
    
    public static void showHeader(String header, int level) {

        if(header == null) {
            return;
        } 
        
        if(header.isEmpty()) {
            return;
        }

        System.out.println(" ");
        if(level == 1) {
            System.out.println("===========================");
        }

        if(level == 2) {
            System.out.println("---------------------------");
        }

        if(level == 3) {
            System.out.println("- - - - - - - - - - - - - - ");
        }

        //For menus
        if(level == 11) {

        }

        System.out.println(header.toUpperCase());

        if(level == 1) {
            System.out.println("===========================");
        }

        if(level == 2) {
            System.out.println("---------------------------");
        }

        if(level == 3) {
            System.out.println("- - - - - - - - - - - - - - ");
        }

        //for menus
        if(level == 11) {
            System.out.println("---------------------------");
        }

    }
}
