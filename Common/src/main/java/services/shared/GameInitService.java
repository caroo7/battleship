package services.shared;


import models.Player;
import gameLogic.ShipManager;

/**
 * Responsible for preparing players board when game starts
 */
public interface GameInitService {

    /**
     * @param player player for actual client. Is already register by other service
     * @param shipManager manager previously initialized by another service
     */
    void initGame(Player player, ShipManager shipManager);

}