package services;

import models.Player;

public interface ActualPlayerService {

    void setActualPlayer(Player player);

    boolean isActualPlayer(Player player);

}
