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
    public long getRivalAliveAmountOfShips(Player player) throws Exception {
        Board rivalBoard = player == Player.FIRST ? secondPlayerBoard : firstPlayerBoard;
        if(rivalBoard.getShipManager() == null) {
            throw new Exception("Rival board not exists yet");
        }
        return rivalBoard.getShipManager().getAmountOfLeftShips();
    }
}
