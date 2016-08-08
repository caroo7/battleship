package gameLogic;

import java.awt.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShipManager implements Serializable {

    private Set<Ship> ships;

    public void initShips(Set<Ship> ships) {
        this.ships = ships;
    }

    Set<Point> getPointsOfSunkShip(Point point) {
        return getSetOf(point, ship -> ship.getCoordinates());
    }

    Set<Point> getPointsAroundSunkShip(Point point) {
        return getSetOf(point,ship -> ship.getNeighbours());
    }

    private Set<Point> getSetOf( Point point, Function<Ship,Set<Point>> mapper) {

        return ships.stream()
                .filter(ship -> ship.containsPoint(point))
                .map(mapper)
                .flatMap(set -> set.stream())
                .collect(Collectors.toSet());
    }

    public long getAmountOfLeftShips() {
        return ships.stream().filter(Ship::isAlive).count();
    }

    Set<Ship> getAllShips() {
        return Collections.unmodifiableSet(ships);
    }
}
