package services.undisclosed;

import gameLogic.Board;
import gameLogic.CellState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.Map;

public class BoardStateServiceImpl implements BoardStateService {

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Override
    public Map<Point, CellState> getUserBoardState(Player player) {
        Board actualBoard = player == Player.FIRST ? firstPlayerBoard : secondPlayerBoard;
        return actualBoard.representCurrentBoardState();
    }

    @Override
    public Map<Point, CellState> getRivalBoardState(Player player) {
        Board actualBoard = player == Player.FIRST ? secondPlayerBoard : firstPlayerBoard;
        return actualBoard.representCurrentBoardState();
    }
}
