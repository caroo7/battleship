package models;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static models.CellState.*;

public class Board {

    private ShipManager shipManager;

    private Map<Point, Cell> cells = new HashMap<>();

    public void init(ShipManager shipManager) {
        this.shipManager = shipManager;
        cells = BoardInitializer.initBoard(shipManager.getAllShips());
    }

    public Map<Point, CellState> representBoardStateAfterClickingOn(Point point) {
        CellState state = cells.get(point).shoot();
        if (state == SUNK) {
            shipManager.getPointsOfSunkShip(point).stream().forEach(coordinate -> cells.get(coordinate).setState(SUNK));
            shipManager.getPointsAroundSunkShip(point).stream().forEach(coordinate -> cells.get(coordinate).setState(AROUNDSUNK));
        }

        return representCurrentBoardState();
    }

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