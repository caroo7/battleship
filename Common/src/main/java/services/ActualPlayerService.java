package services;

import models.Player;

public interface ActualPlayerService {

    void initializePlayer(Player player);

    boolean isActualPlayer(Player player);

    void changeActualPlayer();

}
