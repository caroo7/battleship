package services.shared;

import java.awt.*;

/**
 * Responsible for shooting to ships located on rival board.
 */
public interface ShootService {

    /**
     * It is used when we clicking on rival board if there is our turn
     * @param point on the board where we want to shoot
     */
    void shootOn(Point point);

    /**
     * Responsible for make volley of shots to shoot in few random places on the board (if cell status is EMPTY or SHIP)
     * @param shootNumber - size of volley of shoots. Determine how many free places will be afflicted
     */
    void randomShoot(int shootNumber);

}
