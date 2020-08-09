package atari;

import atari.GUI.WelcomeFrame;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

public class Main {
    public static void main(String[] args) {
        FlatLaf flatLaf = new FlatDarkPurpleIJTheme();
        FlatLaf.install(flatLaf);
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.init();
    }
}
