package gameLogic;

import configuration.Config;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class BoardInitializer {

    static Map<Point, Cell> initBoard(Set<Ship> ships) {
        Map<Point, Cell> result = new HashMap<>();
        for (int i = 0; i < Config.BOARD_SIZE; i++) {
            for (int j = 0; j < Config.BOARD_SIZE; j++) {
                Point point = new Point(i, j);
                Set<Ship> shipToSetInCell = ships.stream().filter(ship -> ship.containsPoint(point)).collect(Collectors.toSet());
                if (shipToSetInCell.isEmpty()) result.put(point, Cell.createEmptyCell());
                else result.put(point, Cell.createCellWithShip(shipToSetInCell.iterator().next()));
            }
        }
        return result;
    }
}
