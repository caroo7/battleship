package services;

import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerRegistrationServiceImpl implements PlayerRegistrationService {

    private int connectedPlayersCounter = 0;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Override
    public void registerPlayer() throws Exception {
        if (!canPlayerConnect()) {
            throw new Exception("It's already two players in the game, cannot add new player");
        }
        Player player = connectedPlayersCounter % MAX_NUMBERS_OF_PLAYERS == 1 ? Player.USER : Player.OPPONENT;
        actualPlayerService.setActualPlayer(player);
    }

    private boolean canPlayerConnect() throws Exception {
        return ++connectedPlayersCounter <= MAX_NUMBERS_OF_PLAYERS;
    }

    @Override
    public void unregisterPlayer() {
        connectedPlayersCounter--;
    }

    @Override
    public int getConnectedPlayersNumber() {
        return connectedPlayersCounter;
    }
}