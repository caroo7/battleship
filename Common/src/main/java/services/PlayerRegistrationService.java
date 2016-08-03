package services;

import models.Player;

public interface PlayerRegistrationService {

    int MAX_NUMBERS_OF_PLAYERS = 2;

    void registerPlayer() throws Exception;

    void unregisterPlayer();

    int getConnectedPlayersNumber();

}
