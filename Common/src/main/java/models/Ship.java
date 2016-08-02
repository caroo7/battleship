package models;


import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class Ship implements Serializable {

    private Set<Point> coordinates;
    private Set<Point> neighbours;
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
