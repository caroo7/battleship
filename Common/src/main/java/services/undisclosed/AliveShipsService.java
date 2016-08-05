package services.undisclosed;

import models.Player;

@FunctionalInterface
public interface AliveShipsService {

    Long getRivalAliveAmountOfShips(Player player);

}
