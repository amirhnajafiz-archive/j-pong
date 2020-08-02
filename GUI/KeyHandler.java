package atari.GUI;

import atari.Model.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Player player1;
    private Player player2;

    public KeyHandler(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player1.GoUp = true;
                break;
            case KeyEvent.VK_S:
                player1.GoDown = true;
                break;
            case KeyEvent.VK_UP:
                player2.GoUp = true;
                break;
            case KeyEvent.VK_DOWN:
                player2.GoDown = true;
                break;
            case KeyEvent.VK_ESCAPE:
                player1.gameOver = true;
                player2.gameOver = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player1.GoUp = false;
                break;
            case KeyEvent.VK_S:
                player1.GoDown = false;
                break;
            case KeyEvent.VK_UP:
                player2.GoUp = false;
                break;
            case KeyEvent.VK_DOWN:
                player2.GoDown = false;
                break;
        }
    }
}
