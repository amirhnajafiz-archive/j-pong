package atari;

import atari.GUI.WelcomeFrame;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

/**
 * Hello welcome to the Atari Pong, a simple old game which we
 * all loved.
 * This game was created in 1972 by Allan Alcorn and it is a arcade game type.
 * I decided to recreate this game by java language.
 * This java app basically uses 6 main class plus two frame classes.
 * I used 'buffer strategy' and 'threads sleep' to keep the game lags as low as
 * possible. The idea of the game is very helpful to create any games with java.
 * Just see the java documents for any extra information about the classes.
 *
 * @author AmirHossein NajafiZadeh
 * @version 0.0.1
 * @serial OFF1380
 * @since 09-07-2020
 */
public class Main {
    public static void main(String[] args) {
        FlatLaf flatLaf = new FlatDarkPurpleIJTheme();
        FlatLaf.install(flatLaf);
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        welcomeFrame.init();
    }
}
