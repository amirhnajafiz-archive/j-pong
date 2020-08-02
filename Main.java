package atari;

import atari.Controller.AreaController;
import atari.GUI.GameFrame;
import atari.Model.GameLoop;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame("Pong");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AreaController.areaHeight = gameFrame.getHeight();
        AreaController.areaWidth = gameFrame.getWidth();
        GameLoop gameLoop = new GameLoop(gameFrame);
        gameLoop.start();
    }
}
