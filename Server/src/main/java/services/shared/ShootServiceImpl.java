package services.shared;

import models.Board;
import models.CellState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.undisclosed.ActualPlayerService;

import java.awt.*;
import java.util.Map;

public class ShootServiceImpl implements ShootService {

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Override
    public void shootOn(Point point) {
        Player actualPlayer = actualPlayerService.getActualPlayer();
        Board actualBoard = actualPlayer == Player.FIRST ? secondPlayerBoard : firstPlayerBoard;

        Map<Point, CellState> resultMap = actualBoard.representCurrentBoardState();
        CellState cellState = resultMap.get(point);

        if(cellState == CellState.EMPTY || cellState == CellState.SHIP) {
            resultMap = actualBoard.representBoardStateAfterClickingOn(point);
            if (resultMap.get(point) == CellState.SHOOTEDEMPTY) {
                actualPlayerService.changeActualPlayer();
            }
        }
    }

}