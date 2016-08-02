package services.undisclosed;

import models.Board;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class AliveShipsServiceImpl implements AliveShipsService {

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Override
    public Long getRivalAliveAmountOfShips(Player player){
        Board rivalBoard = (player == Player.FIRST) ? secondPlayerBoard : firstPlayerBoard;
        return rivalBoard.getShipManager() == null? null : rivalBoard.getShipManager().getAmountOfLeftShips();
    }
}