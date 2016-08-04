
package gameLogic;

import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ShipManager implements Serializable {

    private Set<Ship> ships;

    public void initShips(Set<Ship> ships) {
        this.ships = ships;
    }

    Set<Point> getPointsAroundSunkShip(Point point) {
        Set<Point> aroundSunkCells = new HashSet<>();
        ships.stream()
                .filter(ship -> ship.containsPoint(point))
                .forEach(ship -> ship.getNeighbours()
                        .stream()
                        .forEach(aroundSunkCells::add));
        return aroundSunkCells;
    }

    Set<Point> getPointsOfSunkShip(Point point) {
        Set<Point> sunkCells = new HashSet<>();
        ships.stream()
                .filter(ship -> ship.containsPoint(point))
                .forEach(ship -> ship.getCoordinates()
                        .stream()
                        .forEach(sunkCells::add));
        return sunkCells;
    }

    public long getAmountOfLeftShips() {
        return ships.stream().filter(Ship::isAlive).count();
    }

    Set<Ship> getAllShips() {
        return Collections.unmodifiableSet(ships);
    }
}
