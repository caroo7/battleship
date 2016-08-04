package services.shared;

import gameLogic.Board;
import models.Player;
import gameLogic.ShipManager;
import org.springframework.beans.factory.annotation.Autowired;

public class GameInitServiceImpl implements GameInitService {

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Override
    public void initGame(Player player, ShipManager shipManager) {
        Board actualBoard = ((player == Player.FIRST) ? firstPlayerBoard : secondPlayerBoard);
        actualBoard.init(shipManager);
    }
}
