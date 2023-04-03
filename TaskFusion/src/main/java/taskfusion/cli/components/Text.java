package taskfusion.cli.components;

public class Text {
    

    public static void showInputPrompt(String prompt) {
        System.out.println(" ");
        System.out.print("❯ " + prompt + ": ");
    }

    public static void showInstruction(String InstructionMessage) {
        System.out.println("ℹ️  " + InstructionMessage);
    }

    public static void showSuccess(String successMessage) {
        System.out.println(" ");
        System.out.println("✅  " + successMessage);
    }

    public static void showInfo(String inforMessage) {
        System.out.println("☐  " + inforMessage);
    }

    public static void showError(String errorMessage) {
        System.out.println(" ");
        System.out.println("‼️  " + errorMessage);
    }

    public static void showExceptionError(Exception exception) {
        Text.showError(exception.getMessage());
    }
}
