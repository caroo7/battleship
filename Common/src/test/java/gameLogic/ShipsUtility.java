package gameLogic;

import configuration.Config;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ShipsUtility {

    public static Ship createShipWithoutNeighbours(Point ... point) {
        Set<Point> coordinates = new HashSet<>();
        for (int i = 0; i < point.length; i++) {
            coordinates.add(point[i]);
        }
        return new Ship(coordinates, null);
    }

   public static Ship createShipWithNeighbours(Set<Point> ship, Set<Point> neighbours) {
       return new Ship(ship,neighbours);
    }
}
