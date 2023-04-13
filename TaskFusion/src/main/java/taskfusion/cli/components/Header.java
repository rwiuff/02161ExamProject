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
        Line.showLine(level);
        System.out.println(header.toUpperCase());
        Line.showLine(level);

    }
}
