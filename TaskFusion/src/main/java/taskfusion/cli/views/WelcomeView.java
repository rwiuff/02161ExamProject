package taskfusion.cli.views;

import taskfusion.cli.components.AsciiArt;
import taskfusion.cli.components.Text;

public class WelcomeView implements ViewInterface {
    
    public void show() {
        AsciiArt.showLogo();
        Text.showSlogan("Velkommen til TaskFusion - Fusionen af organisation og produktivitet.");
    }
}
