package taskfusion.cli.components;

public class Text {
    
    public static void showInstruction(String InstructionMessage) {
        System.out.println("# " + InstructionMessage);
    }

    public static void showSuccess(String successMessage) {
        System.out.println("✅ " + successMessage);
    }

    public static void showInfo(String inforMessage) {
        System.out.println("ℹ️ " + inforMessage);
    }

    public static void showError(Exception exception) {
        System.out.println("‼️ " + exception.getMessage());
    }
}
