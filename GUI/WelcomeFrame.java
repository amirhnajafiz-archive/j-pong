package atari.GUI;

import atari.Controller.AreaController;
import atari.Model.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the open game frame.
 */
public class WelcomeFrame extends JFrame {
    private JLabel logo = new JLabel("Atari Pong");
    private String[] levels = {"Easy", "Intermediate", "Pro", "Legend"};
    private JButton singlePlayer, multiPlayer, exitButton;
    private JComboBox<String> gameLevel = new JComboBox<>(levels);

    /**
     * Class constructor.
     */
    public WelcomeFrame() {
        setTitle("Pong game");
        setSize(700, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        JPanel c = new JPanel();
        c.setLayout(null);
        c.setBackground(Color.BLACK);
        logo.setFont(new Font(logo.getFont().getName(), logo.getFont().getStyle(), 24));
        logo.setForeground(Color.WHITE);
        logo.setSize(200, 25);
        logo.setLocation(290, 10);
        singlePlayer = new JButton("Single");
        singlePlayer.setSize(100, 25);
        singlePlayer.setLocation(300, 50);
        multiPlayer = new JButton("Multi");
        multiPlayer.setSize(100, 25);
        multiPlayer.setLocation(300, 100);
        gameLevel.setSize(100, 25);
        gameLevel.setLocation(300, 150);
        exitButton = new JButton("Exit");
        exitButton.setSize(100, 25);
        exitButton.setLocation(300, 200);
        c.add(logo);
        c.add(singlePlayer);
        c.add(multiPlayer);
        c.add(gameLevel);
        c.add(exitButton);
        add(c);
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        singlePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTheGame(true);
            }
        });
        multiPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTheGame(false);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void startTheGame(boolean isSingle) {

        setVisible(false); // Close the welcome

        GameFrame gameFrame = new GameFrame("Pong");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AreaController.areaHeight = gameFrame.getHeight();
        AreaController.areaWidth = gameFrame.getWidth();

        GameLoop gameLoop = new GameLoop(gameFrame, isSingle, gameLevel.getSelectedIndex() + 1);
        gameLoop.start(); // Starting the game
    }
}
