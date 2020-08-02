package atari.Baller;

import atari.Controller.AreaController;

public class Ball {

    public int locX, locY, diam, currentDirection;
    private VectorFactory vectorFactory;
    public boolean over = false;

    public Ball(int locX, int locY, int currentDirection) {
        this.locX = locX;
        this.locY = locY;
        diam = 4;
        this.currentDirection = currentDirection;
        vectorFactory = new VectorFactory(5);
    }

    public void update() {
        if (locY <= AreaController.UP_BOUND || locY >= AreaController.areaHeight - AreaController.DOWN_BOUND - 10)
            currentDirection = 360 - currentDirection;
        int result = AreaController.hitThePlayer(locX, locY, diam, diam);
        if (result != 0) {
            currentDirection = 180 - currentDirection;
            int bound = AreaController.power;
            currentDirection += bound;
        }
        result = AreaController.gotScore(locX, diam);
        if (result != 0)
            over = true;
        vectorFactory.setTheta(currentDirection);
        vectorFactory.solveTheorem(1);
        locX += (int) vectorFactory.x;
        locY += (int) vectorFactory.y;
    }
}

