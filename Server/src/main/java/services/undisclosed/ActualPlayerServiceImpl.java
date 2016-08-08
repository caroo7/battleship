package services.undisclosed;

import models.Player;

/**
 * Set of operations for determining who is actual player during the game and changing him
 */
public class ActualPlayerServiceImpl {

    private Player actualPlayer;

    /**
     * Always first connected player is the first who'll start the game
     */
    public void setActualPlayerAsFirstPlayerAtStart() {
        actualPlayer = Player.FIRST;
    }

    /**
     * Retrieve actual player instance.
     * @return first or second player - depends who is actual player at the moment.
     */
    public Player getActualPlayer() {
        return actualPlayer;
    }

    /**
     * switch actual player
     */
    public void changeActualPlayer() {
        actualPlayer = actualPlayer == Player.FIRST ? Player.SECOND : Player.FIRST;
    }
}
