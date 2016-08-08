package gameLogic;

import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <h1>Class {@code ShipManager} </h1>
 * represent manager, which is responsible for <b>retrieving</b> and <b>calculation</b>
 * information about ships on the {@link Board}, {@code ShipManager} instance belongs to.
 *
 * @version 1.0
 * @since 2016-08-08
 */

public class ShipManager implements Serializable {

    private Set<Ship> ships;

    /**
     * The {@code initShips} method should be invoked before any others.
     * It's used instead constructor for purpose of later initializing.
     * @param ships A Set of all ships on the {@link Board}
     */
    public void initShips(Set<Ship> ships) {
        this.ships = ships;
    }

    /**
     *Retrieves points which belongs to sunk ship.
     * @param point One of the points from sunk ship
     * @return {@link java.util.Set} of {@link java.awt.Point},which represents points of sunk ship including point from method argument.
     */
    Set<Point> getPointsOfSunkShip(Point point) {
        return getSetOf(point, ship -> ship.getCoordinates());
    }

    /**
     *Retrieves points which represent cells around sunk ship.
     * @param point One of the points from sunk ship
     * @return {@link java.util.Set} of {@link java.awt.Point},which represents points of sunk ship including point from method argument.
     */
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

    /**
     * Traverses through the Set of ships and calculate how many ships are not sunk.
     * @return number of not sunk ships.
     */
    public long getAmountOfLeftShips() {
        return ships.stream().filter(Ship::isAlive).count();
    }

    Set<Ship> getAllShips() {
        return Collections.unmodifiableSet(ships);
    }
}
