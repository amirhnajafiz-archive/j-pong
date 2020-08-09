package atari.Baller;

import atari.Controller.AreaController;

public class Ball {

    public int locX, locY, diam, currentDirection;
    public boolean over = false;
    private VectorFactory vectorFactory;

    /**
     * The class constructor.
     * @param locX the first x position
     * @param locY the first y position
     * @param currentDirection the first direction
     */
    public Ball(int locX, int locY, int currentDirection) {
        //
        this.locX = locX;
        this.locY = locY;
        //
        diam = 5;
        this.currentDirection = currentDirection;
        //
        vectorFactory = new VectorFactory(5);
    }

    /**
     * This method will update the ball movement.
     */
    public void update() {

        frameLimitCheck();
        directionChange();
        erectCheck();

        over = AreaController.gotScore(locX, diam) != 0;

        vectorFactory.setTheta(currentDirection);
        vectorFactory.solveTheorem(1);

        locX += (int) vectorFactory.x;
        locY += (int) vectorFactory.y;
    }

    private void frameLimitCheck() {
        if (locY <= AreaController.UP_BOUND || locY >= AreaController.areaHeight - AreaController.DOWN_BOUND - 10)
            currentDirection = 360 - currentDirection; // Check if the ball hits the frame borders.
    }

    private void directionChange() {
        vectorFactory.setTheta(currentDirection);
        vectorFactory.solveTheorem(1); // Find a spot little farther from out current position on the direction vector.
        if (AreaController.hitThePlayer(locX + (int) vectorFactory.x, locY + (int) vectorFactory.y) != 0) {
            currentDirection = 180 - currentDirection;
            currentDirection += AreaController.power;
        }
    }

    private void erectCheck() {
        // If the ball direction gets to 90 or 270 it will stuck in that status for ever
        // so we avoid it.
        int check = Math.abs(currentDirection % 360);
        if (check == 90)
            if (locX <= 500)
                currentDirection -= 30;
            else
                currentDirection += 30;
        if (check == 270)
            if (locX >= 500)
                currentDirection -= 30;
            else
                currentDirection += 30;
    }
}