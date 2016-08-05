package services.shared;

import gameLogic.Board;
import gameLogic.CellState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.undisclosed.ActualPlayerService;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ShootServiceImpl implements ShootService {

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Override
    public void shootOn(Point point) {
        Map<Point, CellState> boardStateAfterShoot = retrieveBoardAfterShoot(point);
        if (boardStateAfterShoot.get(point) == CellState.SHOOTEDEMPTY) {
            actualPlayerService.changeActualPlayer();
        }
    }

    @Override
    public void randomShoot(int shootNumber) {
        Map<Point, CellState> boardState = getActualBoardState().representCurrentBoardState();
        List<Point> emptyCells = boardState.keySet()
                .stream().filter(point -> boardState.get(point) == CellState.EMPTY || boardState.get(point) == CellState.SHIP)
                .collect(Collectors.toList());

        for (int i = 0; i < shootNumber; i++) {
            if (checkIfShootIsPossible(emptyCells)) {
                return;
            }
            retrieveBoardAfterShoot(getPointToShoot(emptyCells));
        }
        actualPlayerService.changeActualPlayer();
    }

    private boolean checkIfShootIsPossible(List<Point> emptyCells) {
        if (emptyCells.size() == 0) actualPlayerService.changeActualPlayer();
        return emptyCells.size() == 0;
    }

    private Point getPointToShoot(List<Point> emptyCells) {
        Random random = new Random();
        Point pointToShoot = emptyCells.get(random.nextInt(emptyCells.size()));
        emptyCells.remove(pointToShoot);
        return pointToShoot;
    }

    private Map<Point, CellState> retrieveBoardAfterShoot(Point point) {
        Map<Point, CellState> resultMap = getActualBoardState().representCurrentBoardState();
        CellState cellState = resultMap.get(point);
        return (cellState == CellState.EMPTY || cellState == CellState.SHIP) ? getActualBoardState().representBoardStateAfterClickingOn(point) : resultMap;
    }

    private Board getActualBoardState() {
        Player actualPlayer = actualPlayerService.getActualPlayer();
        return actualPlayer == Player.FIRST ? secondPlayerBoard : firstPlayerBoard;
    }
}