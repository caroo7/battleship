package gameLogic;

import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static gameLogic.CellState.AROUNDSUNK;
import static gameLogic.CellState.SUNK;


public class Board {

    @Autowired
    public Player owner;
    @Autowired
    private ShipManager shipManager;

    private Map<Point, Cell> cells;

    public void init() {
        cells = BoardInitializer.initBoard(shipManager.getAllShips());
    }

    public Map<Point, CellState> representBoardStateAfterClickingOn(Point point) {
        CellState state = cells.get(point).shoot();
        if (state == SUNK) {
            shipManager.getPointsOfSunkShip(point).stream().forEach(coordinate -> cells.get(coordinate).state = SUNK);
            shipManager.getPointsAroundSunkShip(point).stream().forEach(coordinate -> cells.get(coordinate).state = AROUNDSUNK);
        }
        return representCurrentBoardState();
    }

    public Map<Point, CellState> representCurrentBoardState() {
        Map<Point, CellState> result = new HashMap<>();
        Set<Point> points = cells.keySet();
        for (Point point : points) {
            result.put(point, cells.get(point).state);
        }
        return result;
    }

}
