package gameLogic;

import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ShipManager implements Serializable {

    private Set<Ship> ships;

    public void initShips(Set<Ship> ships) {
        this.ships = ships;
    }

    Set<Point> getPointsAroundSunkShip(Point point) {
        return getSetOf(PointsStatus.AROUNDSUNK, point);
    }

    Set<Point> getPointsOfSunkShip(Point point) {
        return getSetOf(PointsStatus.SUNK, point);
    }

    enum PointsStatus {
        AROUNDSUNK, SUNK;
    }

    private Set<Point> getSetOf(PointsStatus status, Point point) {
        Set<Point> result = new HashSet<>();
        ships.stream()
                .filter(ship -> ship.containsPoint(point))
                .forEach(ship -> {
                    if (status == PointsStatus.AROUNDSUNK) {
                        ship.getNeighbours()
                                .stream()
                                .forEach(result::add);
                    } else {
                        ship.getCoordinates()
                                .stream()
                                .forEach(result::add);
                    }
                });
        return result;
    }


    public long getAmountOfLeftShips() {
        return ships.stream().filter(Ship::isAlive).count();
    }

    Set<Ship> getAllShips() {
        return Collections.unmodifiableSet(ships);
    }
}
