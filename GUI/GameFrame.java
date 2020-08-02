package atari.GUI;

import atari.Baller.Ball;
import atari.Controller.AreaController;
import atari.Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameFrame extends JFrame{

    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;
    private BufferStrategy bufferStrategy;

    public GameFrame(String title) {
        super(title);
        setResizable(false);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLocationRelativeTo(null);
        setFocusable(true);
    }

    public void initBufferStrategy() {
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }

    public void render(Player player1, Player player2, Ball ball) {
        do {
            do {
                Graphics2D graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
                doRender(graphics2D, player1, player2, ball);
                graphics2D.dispose();
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
            Toolkit.getDefaultToolkit().sync();
        } while (bufferStrategy.contentsLost());
    }

    private void doRender(Graphics2D g2d, Player player1, Player player2, Ball ball) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, FRAME_WIDTH, FRAME_HEIGHT);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 25, FRAME_HEIGHT);
        g2d.fillRect(FRAME_WIDTH - 25, 0, 25, FRAME_HEIGHT);
        g2d.setFont(new Font("Tahoma",26,50));
        g2d.drawString(String.valueOf(player1.score), 450, 70);
        g2d.drawString(String.valueOf(player2.score), 510, 70);
        for (int i = 0; i < 10; i++)
            g2d.fillRect(490, i * 50, 10, 20);
        g2d.fillRect(player1.locX, player1.locY, player1.WIDTH, player1.HEIGHT);
        g2d.fillRect(player2.locX, player2.locY, player2.WIDTH, player2.HEIGHT);
        g2d.fillOval(ball.locX, ball.locY, ball.diam, ball.diam);
        if (ball.over) {
            g2d.setFont(new Font("Arial", 1, 50));
            g2d.setColor(Color.YELLOW);
            String show;
            if (AreaController.winner == 1)
                show = "Player 1 Scored";
            else
                show = "Player 2 Scored";
            g2d.drawString(show, 300, 250);
        } else if (player1.gameOver && player2.gameOver) {
            g2d.setFont(new Font("Arial", 1, 100));
            g2d.setColor(Color.RED);
            g2d.drawString("Game Over", 240, 250);
        }
    }
}
