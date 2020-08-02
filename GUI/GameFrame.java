package atari.GUI;

import atari.Baller.Ball;
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
        g2d.fillRect(player1.locX, player1.locY, player1.WIDTH, player1.HEIGHT);
        g2d.fillRect(player2.locX, player2.locY, player2.WIDTH, player2.HEIGHT);
        g2d.fillOval(ball.locX, ball.locY, ball.diam, ball.diam);
    }
}
