package services.shared;

import gameLogic.Ship;

import java.util.Set;

/**
 * Responsible for set of ships generation. This set will be used in future during the game.
 */
public interface ShipGeneratorService {

    /**
     * It is used during generation phase, before game starts. Creates horizontal and vertical ships randomly.
     * @return created set of ships
     */
    Set<Ship> generateShips();

}