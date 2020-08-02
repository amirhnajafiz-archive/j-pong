package atari.Controller;

import atari.Model.Player;

public class AreaController {

    public static int areaWidth;
    public static int areaHeight;
    public static Player player1, player2;
    public static int power;
    public static final int UP_BOUND = 30;
    public static final int DOWN_BOUND = 5;

    public static boolean areaCheck (int locY, int height) {
        return locY >= UP_BOUND && locY + height <= areaHeight - DOWN_BOUND;
    }

    public static int gotScore (int userX, int width) {
        boolean first = userX < 40;
        boolean second = userX + width > areaWidth - 50;
        if (first)
            return 1;
        if (second)
            return 2;
        else
            return 0;
    }

    public static int hitThePlayer (int userX, int userY, int width, int height) {
        boolean first = (player1.locX < userX && player1.locX + player1.WIDTH > userX || player1.locX < userX + width && player1.locX + player1.WIDTH > userX + width) &&
                (player1.locY < userY && player1.locY + player1.HEIGHT > userY || player1.locY < userY + height && player1.locY + player1.HEIGHT > userY + height);
        boolean second = (player2.locX < userX && player2.locX + player2.WIDTH > userX || player2.locX < userX + width && player2.locX + player2.WIDTH > userX + width) &&
                (player2.locY < userY && player2.locY + player2.HEIGHT > userY || player2.locY < userY + height && player2.locY + player1.HEIGHT > userY + height);
        if (first) {
            power = player1.power;
            return 1;
        }
        else if (second) {
            power = player2.power;
            return 2;
        }
        else
            return 0;
    }
}
