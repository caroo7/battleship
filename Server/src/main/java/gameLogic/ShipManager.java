package gameLogic;

import models.Ship;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ShipManager {

    private Set<Ship> ships;

    // initialize somehow this manager

    public Set<Point> getPointsAroundSunkShip(Point point) {
        Set<Point> aroundSunkCells = new HashSet<>();
        ships.stream().filter(ship -> ship.containsPoint(point)).forEach(ship -> ship.getNeighbours()
                .stream().forEach(coordinate -> aroundSunkCells.add(coordinate)));
        return aroundSunkCells;
    }

    public Set<Point> getPointsOfSunkShip(Point point) {
        Set<Point> sunkCells = new HashSet<>();
        ships.stream().filter(ship -> ship.containsPoint(point)).forEach(ship -> ship.getCoordinates()
                .stream().forEach(coordinate -> sunkCells.add(coordinate)));
        return sunkCells;
    }

    public long getAmountOfLeftShips() {
        return ships.stream().filter(ship -> ship.isAlive()).count();
    }

    public Set<Ship> getAllShips() {
        return Collections.unmodifiableSet(ships);
    }
}
