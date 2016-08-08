package services.shared;

import exceptions.PlayerRegistrationException;
import models.Player;

/**
 * Responsible for register player when he starts game
 */
public interface PlayerRegistrationService {

    int MAX_NUMBERS_OF_PLAYERS = 2;

    /**
     * Responsible for player registration where there is less than two players in the game
     * @return registered player
     * @throws PlayerRegistrationException - throw when third player try to connect to the game - it's forbidden
     */
    Player registerPlayer() throws PlayerRegistrationException;

    /**
     * @return number of already connected players at specific moment
     */
    int getConnectedPlayersNumber();

    /**
     * Remove player from the game
     */
    void unregisterPlayer();

    /**
     * Will be invoked when win condition happens.
     */
    void unregisterBothPlayers();

}
