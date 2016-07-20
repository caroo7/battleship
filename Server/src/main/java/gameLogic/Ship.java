package gameLogic;


import java.awt.*;
import java.util.Collections;
import java.util.Set;

public class Ship {

    private Set<Point> coordinates;
    private Set<Point> neighbours;
    private int alivePartOfShipLeft;


    public Ship(Set<Point> coordinates, Set<Point> neighbours) {
        this.coordinates = coordinates;
        this.neighbours = neighbours;
        this.alivePartOfShipLeft = coordinates.size();
    }

    public boolean isAlive() {
        return (--alivePartOfShipLeft)!=0;
    }


    public Set<Point> getCoordinates() {
        return Collections.unmodifiableSet(coordinates);
    }

    public Set<Point> getNeighbours() {
        return Collections.unmodifiableSet(neighbours);
    }

    public boolean containsPoint(Point point) {
        return coordinates.contains(point);
    }

}
