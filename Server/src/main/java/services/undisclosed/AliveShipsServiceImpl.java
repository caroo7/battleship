package services.undisclosed;

import gameLogic.Board;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class AliveShipsServiceImpl implements AliveShipsService {

    @Autowired
    Board firstPlayerBoard;

    @Autowired
    Board secondPlayerBoard;

    @Override
    public Long getRivalAliveAmountOfShips(Player player){
        Board rivalBoard = (player == Player.FIRST) ? secondPlayerBoard : firstPlayerBoard;
        return rivalBoard.getShipManager() == null? null : rivalBoard.getShipManager().getAmountOfLeftShips();
    }
}