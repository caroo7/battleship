package services.shared;


import models.Player;
import gameLogic.ShipManager;

public interface GameInitService {

    void initGame(Player player, ShipManager shipManager);

}