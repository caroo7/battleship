package services.undisclosed;

import models.Board;
import models.CellState;
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
        Map<Point, CellState> boardState = actualBoard.representCurrentBoardState();

        boardState.keySet().stream().forEach(point -> {
            if (boardState.get(point) == CellState.SHIP) {
                boardState.put(point, CellState.EMPTY);
            }
        });
        return boardState;
    }
}
