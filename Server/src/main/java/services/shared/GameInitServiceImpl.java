package services.shared;

import gameLogic.Board;
import models.Player;
import gameLogic.ShipManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Responsible for preparing players board when game starts
 */
public class GameInitServiceImpl implements GameInitService {

    /**
     * Contains first player board statuses
     */
    @Autowired
    private Board firstPlayerBoard;

    /**
     * Contains second player board statuses
     */
    @Autowired
    private Board secondPlayerBoard;

    /**
     * According to player it initialize proper board
     * @param player player for actual client. At this moment he is already register by other service
     * @param shipManager manager previously initialized by another service. Contains already generated set of ships.
     */
    @Override
    public void initGame(Player player, ShipManager shipManager) {
        Board actualBoard = ((player == Player.FIRST) ? firstPlayerBoard : secondPlayerBoard);
        actualBoard.init(shipManager);
    }

}