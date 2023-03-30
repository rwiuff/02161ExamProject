package taskfusion.cli.views;

import taskfusion.cli.components.AsciiArt;

public class WelcomeView implements ViewInterface {
    
    public void show() {
        AsciiArt.showLogo();
        System.out.println("Velkommen til TaskFusion - Fusionen af organisation og produktivitet.");
    }
}
