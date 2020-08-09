package atari.Controller;

import atari.Model.Player;

/**
 * This class controls the status between players
 * and the ball for any overlapping.
 */
public class AreaController {
    public static final int UP_BOUND = 30, DOWN_BOUND = 5; // Frame bounds
    public static int areaWidth, areaHeight, power, winner, angleBound = 10;
    public static Player player1, player2;

    public static boolean areaCheck (int locY, int height) { return locY >= UP_BOUND && locY + height <= areaHeight - DOWN_BOUND; }

    public static int gotScore (int userX, int width) {
        winner = userX < 40 ? 1 : userX + width > areaWidth - 50 ? 2 : 0;
        return winner;
    }

    public static int hitThePlayer (int userX, int userY) {
        if (hitCheck(userX, userY, player1)) {
            if (player1.locY + player1.HEIGHT / 2 > userY)
                power = angleBound;
            else if (player1.locY + player1.HEIGHT / 2 < userY)
                power = -1 * angleBound;
            else
                power = 0;
            return 1;
        }
        else if (hitCheck(userX, userY, player2)) {
            if (player2.locY + player2.HEIGHT / 2 > userY)
                power = angleBound;
            else if (player2.locY + player2.HEIGHT < userY)
                power = -1 * angleBound;
            else
                power = 0;
            return 2;
        }
        else {
            power = 0;
            return 0;
        }
    }

    private static boolean hitCheck(int userX, int userY, Player player) {
        return  player.locX <= userX && player.locX + player.WIDTH >= userX && player.locY <= userY && player.locY + player.HEIGHT >= userY;
    }
}