package services.shared;

import exceptions.PlayerRegistrationException;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.undisclosed.ActualPlayerServiceImpl;

/**
 * Responsible for register player when he starts game
 */
public class PlayerRegistrationServiceImpl implements PlayerRegistrationService {

    private int connectedPlayersCounter = 0;

    @Autowired
    ActualPlayerServiceImpl actualPlayerService;

    /**
     * Responsible for player registration in game where there is already less than two players in the game.
     * It also set first player as actual.
     * @return registered player (could be first or second - depends of moment of registration)
     * @throws PlayerRegistrationException - throw when third player try to connect to the game
     */
    @Override
    public Player registerPlayer() throws PlayerRegistrationException {
        if (!canPlayerConnect()) {
            throw new PlayerRegistrationException("It's already two players in the game, cannot add new player");
        }
        Player player = connectedPlayersCounter % MAX_NUMBERS_OF_PLAYERS == 1 ? Player.FIRST : Player.SECOND;
        actualPlayerService.setActualPlayerAsFirstPlayerAtStart();
        return player;
    }

    private boolean canPlayerConnect() throws PlayerRegistrationException {
        return ++connectedPlayersCounter <= MAX_NUMBERS_OF_PLAYERS;
    }

    /**
     * @return number of already connected players at specific moment
     */
    @Override
    public int getConnectedPlayersNumber() {
        return connectedPlayersCounter;
    }

    /**
     * Remove player from the game. Now implementation is dummy and just decrement counter. If one player close his window he will be unregister and game will close.
     */
    @Override
    public void unregisterPlayer() {
        connectedPlayersCounter--;
    }

    /**
     * Will be invoked when win condition happens. No more players in the game.
     */
    @Override
    public void unregisterBothPlayers() {
        connectedPlayersCounter = 0;
    }
}