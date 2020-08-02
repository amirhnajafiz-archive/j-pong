package atari.Baller;

import atari.Controller.AreaController;

public class Ball {

    public int locX, locY, diam, currentDirection;
    private VectorFactory vectorFactory;
    public boolean over = false;

    public Ball(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
        diam = 4;
        this.currentDirection = 0;
        vectorFactory = new VectorFactory(5);
    }

    public void update() {
        if (locY <= AreaController.UP_BOUND || locY >= AreaController.areaHeight - AreaController.DOWN_BOUND)
            currentDirection = 360 - currentDirection;
        int result = AreaController.hitThePlayer(locX, locY, diam, diam);
        if (result == 1)
            currentDirection = 0;
        if (result == 2)
            currentDirection = 180;
        result = AreaController.gotScore(locX, diam);
        if (result != 0)
            over = true;
        vectorFactory.setTheta(currentDirection);
        vectorFactory.solveTheorem(1);
        locX += (int) vectorFactory.x;
        locY += (int) vectorFactory.y;
    }
}

