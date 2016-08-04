package services.shared;

import models.Player;

public interface PlayerRegistrationService {

    int MAX_NUMBERS_OF_PLAYERS = 2;

    Player registerPlayer() throws Exception;

    int getConnectedPlayersNumber();

    void unregisterPlayer();

    void unregisterBothPlayers();

}
