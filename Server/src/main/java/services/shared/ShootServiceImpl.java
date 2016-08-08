package services.shared;

import gameLogic.Board;
import gameLogic.CellState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.undisclosed.ActualPlayerServiceImpl;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Responsible for shooting to ships located on rival board.
 */
public class ShootServiceImpl implements ShootService {

    @Autowired
    private ActualPlayerServiceImpl actualPlayerService;

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
     * It is used when we clicking on rival board if there is our turn.
     * Determine the actual player based on ActualPlayerService and choose proper board.
     * Update board according to shoot and change actual player where player who shoot missed.
     * @param point on the board where we want to shoot
     */
    @Override
    public void shootOn(Point point) {
        Map<Point, CellState> boardStateAfterShoot = retrieveBoardAfterShoot(point);
        if (boardStateAfterShoot.get(point) == CellState.SHOOTEDEMPTY) {
            actualPlayerService.changeActualPlayer();
        }
    }

    /**
     * Responsible for make volley of shots in few random places on the board (if cell status is EMPTY or SHIP).
     * Looking for all places on the chosen board where player can shoot. Randomly select appropriate number of places and always change player at the end.
     * @param shootNumber - size of volley of shoots. Determine how many free places will be afflicted.
     */
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