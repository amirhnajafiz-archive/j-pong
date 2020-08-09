package atari.Model;

import atari.Controller.AreaController;

public class Player {

    public final int WIDTH, HEIGHT;
    public boolean GoUp, GoDown, gameOver, gamePause, isBot;
    public int locX, locY, score;
    private final int level;

    /**
     * The class constructor.
     * @param width player width
     * @param height player height
     * @param locX player first x
     * @param locY player first y
     */
    public Player (int width, int height, int locX, int locY, int level) {
        //
        WIDTH = width;
        HEIGHT = height;
        this.locX = locX;
        this.locY = locY;
        //
        score = 0;
        this.level = level;
        //
        GoDown = false;
        GoUp = false;
    }

    public void update() {
        if (GoUp)
            if (AreaController.areaCheck(locY - 5, HEIGHT))
                locY -= 5;
        if (GoDown)
            if (AreaController.areaCheck(locY + 5, HEIGHT))
                locY += 5;
    }

    public AI getAI(int y, int direction) {
        return new AI(y, direction);
    }

    private class AI implements Runnable {
        private int ballY, direction;

        AI (int y, int direction) {
            ballY = y;
            this.direction = direction;
        }

        @Override
        public void run() {
            direction = direction % 360;
            direction += direction < 0 ? 360 : 0;
            if (direction > 90 && direction < 270)
                return;
            if (locY + HEIGHT / 2 < ballY)
                if (AreaController.areaCheck(locY + level, HEIGHT))
                    locY += level;
            if (locY + HEIGHT / 2 > ballY)
                if (AreaController.areaCheck(locY - level, HEIGHT))
                    locY -= level;
        }
    }
}
