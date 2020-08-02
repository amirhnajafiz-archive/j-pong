package atari.Model;

import atari.Baller.Ball;
import atari.Controller.AreaController;
import atari.GUI.GameFrame;
import atari.GUI.KeyHandler;

public class GameLoop extends Thread {

    public static final int FPS = 30;

    private GameFrame gameFrame;
    private Player player1, player2;
    private Ball ball;

    public GameLoop(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        player1 = new Player(10, 20, 50, 240);
        player2 = new Player(10,20, gameFrame.getWidth() - 60, 240);
        AreaController.player1 = player1;
        AreaController.player2 = player2;
        ball = new Ball(gameFrame.getWidth() / 2, gameFrame.getHeight() / 2);
        this.gameFrame.addKeyListener(new KeyHandler(player1, player2));
        gameFrame.setVisible(true);
        gameFrame.initBufferStrategy();
    }

    @Override
    public void run() {
        while (!ball.over) {
            long start = System.currentTimeMillis();
            ball.update();
            player1.update();
            player2.update();
            gameFrame.render(player1, player2, ball);
            long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
