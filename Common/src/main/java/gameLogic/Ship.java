package gameLogic;


import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * Class {@code Ship} represent ship in Battleship game.
 * In it's state it contains information about {@link java.awt.Point} points of
 * two types: <b>coordinates</b> - points, on which ship is located
 * and <b>neighbours</b> - points, which belong to {@link Cell} cells around the ship.
 */
public class Ship implements Serializable {

    private final Set<Point> coordinates;
    private final Set<Point> neighbours;
    private int alivePartOfShipLeft;


    public Ship(Set<Point> coordinates, Set<Point> neighbours) {
        this.coordinates = coordinates;
        this.neighbours = neighbours;
        this.alivePartOfShipLeft = coordinates.size();
    }

    public boolean isAlive() {
        return alivePartOfShipLeft !=0;
    }

    void reduceShipParts() {
        alivePartOfShipLeft--;
    }

    public Set<Point> getCoordinates() {
        return Collections.unmodifiableSet(coordinates);
    }

    Set<Point> getNeighbours() {
        return Collections.unmodifiableSet(neighbours);
    }

    boolean containsPoint(Point point) {
        return coordinates.contains(point);
    }

}
