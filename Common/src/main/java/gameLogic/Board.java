package gameLogic;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static gameLogic.CellState.AROUNDSUNK;
import static gameLogic.CellState.SUNK;

/**
 * Class {@Board} represents game board for Battleship game.
 *It has reference to {@link ShipManager} shipManager, which works with ships
 * and <b>cells</b> field, which represents state of the game in terms of {@link Cell}.
 */
public class Board {

    private ShipManager shipManager;
    private Map<Point, Cell> cells = new HashMap<>();

    public void init(ShipManager shipManager) {
        this.shipManager = shipManager;
        cells = BoardInitializer.initBoard(shipManager.getAllShips());
    }

    /**
     * This method should be invoked when click on any cell on the board
     * to change the state of the board and to return actual board state.
     * @param point is point, which was clicked on.
     * @return actual board state.
     */
    public Map<Point, CellState> representBoardStateAfterClickingOn(Point point) {
        CellState state = cells.get(point).shoot();
        if (state == SUNK) {
            shipManager.getPointsOfSunkShip(point).stream().forEach(coordinate -> cells.get(coordinate).setState(SUNK));
            shipManager.getPointsAroundSunkShip(point).stream().forEach(coordinate -> cells.get(coordinate).setState(AROUNDSUNK));
        }
        return representCurrentBoardState();
    }

    /**
     * This method should be invoked in some period of time to
     * have <b>fresh</b> information about board state.
     * @return actual board state.
     */
    public Map<Point, CellState> representCurrentBoardState() {
        Map<Point, CellState> result = new HashMap<>();
        Set<Point> points = cells.keySet();
        for (Point point : points) {
            result.put(point, cells.get(point).getState());
        }
        return result;
    }

    public ShipManager getShipManager() {
        return shipManager;
    }
}