package services.shared;

import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.PlayerRegistrationService;
import services.undisclosed.ActualPlayerService;

public class PlayerRegistrationServiceImpl implements PlayerRegistrationService {

    private int connectedPlayersCounter = 0;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Override
    public Player registerPlayer() throws Exception {
        if (!canPlayerConnect()) {
            throw new Exception("It's already two players in the game, cannot add new player");
        }
        Player player = connectedPlayersCounter % MAX_NUMBERS_OF_PLAYERS == 1 ? Player.FIRST : Player.SECOND;
        actualPlayerService.setActualPlayerAsFirstPlayerAtStart();
        return player;
    }

    private boolean canPlayerConnect() throws Exception {
        return ++connectedPlayersCounter <= MAX_NUMBERS_OF_PLAYERS;
    }

    @Override
    public int getConnectedPlayersNumber() {
        return connectedPlayersCounter;
    }

    @Override
    public void unregisterPlayer() {
        connectedPlayersCounter--;
    }

    @Override
    public void unregisterBothPlayers() {
        connectedPlayersCounter = 0;
    }
}