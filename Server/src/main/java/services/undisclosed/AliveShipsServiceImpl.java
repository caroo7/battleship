package services.undisclosed;

import gameLogic.Board;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Gives information about ships existing on the board
 */
public class AliveShipsServiceImpl {

    /**
     * Contains first player board statuses
     */
    @Autowired
    Board firstPlayerBoard;

    /**
     * Contains second player board statuses
     */
    @Autowired
    Board secondPlayerBoard;


    /**
     * Retrieve rival amount of alive ships.
     * During the game ships are removing from sets if they were destroyed. This method return actual amount of alive ships.
     * @param player - needed to determine rival board. Unlike in other services here we choose rival board. Player.FIRST -> secondBoard, Player.SECOND -> firstBoard
     * @return ships that still exist in the board or null if there is no ship manager (never should happened).
     */
    public Long getRivalAliveAmountOfShips(Player player){
        Board rivalBoard = (player == Player.FIRST) ? secondPlayerBoard : firstPlayerBoard;
        return rivalBoard.getShipManager() == null? null : rivalBoard.getShipManager().getAmountOfLeftShips();
    }
}