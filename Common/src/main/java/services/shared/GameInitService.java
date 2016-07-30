package services.shared;

import models.Player;
import models.Ship;
import models.ShipManager;

import java.util.Set;

public interface GameInitService {

    void initGame(Player player, ShipManager shipManager);

}