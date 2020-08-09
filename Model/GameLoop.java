package atari.Model;

import atari.Baller.Ball;
import atari.Controller.AreaController;
import atari.GUI.GameFrame;
import atari.GUI.KeyHandler;
import atari.Main;

public class GameLoop extends Thread {

    public static final int FPS = 30;
    private GameFrame gameFrame;
    private Player player1, player2;
    private Ball ball;

    public GameLoop(GameFrame gameFrame, boolean isBotGame, int level) {
        //
        this.gameFrame = gameFrame;
        //
        player1 = new Player(10, 50, 50, 240, 2 * level);
        player2 = new Player(10,50, gameFrame.getWidth() - 60, 240, 2 * level);
        if (isBotGame)
            player2.isBot = true;
        AreaController.player1 = player1;
        AreaController.player2 = player2;
        //
        ball = new Ball(gameFrame.getWidth() / 2, gameFrame.getHeight() / 2, 90);
        //
        this.gameFrame.addKeyListener(new KeyHandler(player1, player2));
        gameFrame.setVisible(true);
        gameFrame.initBufferStrategy();
    }

    public void reset() {
        player1.locY = 240;
        player2.locY = 240;
        player1.GoDown = false;
        player1.GoUp = false;
        player2.GoUp = false;
        player2.GoDown = false;
        //
        int direction = 0;
        if (AreaController.winner == 1) {
            direction = 180;
            player2.score++;
        }
        else if (AreaController.winner == 2)
            player1.score++;
        //
        ball = new Ball(gameFrame.getWidth() / 2, gameFrame.getHeight() / 2, direction);
    }

    @Override
    public void run() {
        while (!player1.gameOver && !player2.gameOver) {
            while (!ball.over && !player1.gameOver && !player2.gameOver) {
                long start = System.currentTimeMillis();
                if (!player1.gamePause && !player2.gamePause) {
                    ball.update();
                    player1.update();
                    if (!player2.isBot)
                        player2.update();
                    else {
                        Thread thread = new Thread(player2.getAI(ball.locY, ball.currentDirection));
                        thread.start();
                    }
                }
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
            gameFrame.render(player1, player2, ball);
            reset();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameFrame.render(player1, player2, ball);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameFrame.setVisible(false);
        Main.main(new String[]{"Empty"});
    }
}
