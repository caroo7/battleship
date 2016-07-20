package gameLogic;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardInitializer {

    public static Map<Point, Cell> initBoard(Set<Ship> ships) {
        Map<Point, Cell> result = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Point point = new Point(i, j);
                Set<Ship> shipToSetInCell = ships.stream().filter(ship -> ship.containsPoint(point)).collect(Collectors.toSet());
                if (shipToSetInCell.isEmpty()) result.put(point, Cell.createEmptyCell());
                else result.put(point, Cell.createCellWithShip(shipToSetInCell.iterator().next()));
            }
        }
        return result;
    }
}
