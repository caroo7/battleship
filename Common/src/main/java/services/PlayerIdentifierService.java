package services;

import models.Player;

public interface PlayerIdentifierService {

    Player registerPlayer() throws Exception;

    void unregisterPlayer();

    boolean isGameAvailable();

}
