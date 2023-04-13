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
        System.out.println("✅  \u001B[32m " + successMessage + " \u001B[0m");
    }

    public static void showInfo(String inforMessage) {
        System.out.println("☐  " + inforMessage);
    }

    public static void showError(String errorMessage) {
        System.out.println(" ");
        System.out.println("⚠️ \u001B[31m " + errorMessage + "\u001B[0m");
    }

    public static void showExceptionError(Exception exception) {
        Text.showError(exception.getMessage());
    }

    public static void showSlogan(String slogan) {
        System.out.println("      ~~~~~ " + slogan + " ~~~~~");
    }

}
