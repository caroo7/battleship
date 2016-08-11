package services.shared.ships;

import configuration.Config;
import gameLogic.Ship;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestAllShipGenerations {
    private final int EXPECTED_SHIP_COUNT = 7;

    private final int BOARD_SIZE = Config.BOARD_SIZE;

    private ShipGeneratorServiceImpl shipGeneratorService;

    private Set<Ship> generatedShips;

    private Set<Point> takenCoordinates;

    @BeforeClass()
    public void init() {
        shipGeneratorService = new ShipGeneratorServiceImpl();
        generatedShips = shipGeneratorService.generateShips();
        takenCoordinates = new HashSet<>();
    }


    @Test
    void testAllShipGeneration() {
        assertEquals(generatedShips.size(), EXPECTED_SHIP_COUNT);

        for (Ship ship : generatedShips) {
            int shipLength = ship.getCoordinates().size();
            if (isShipHorizontal(ship)) assertTrue(isHorizontalShipValid(ship, shipLength));
           else assertTrue(isVerticalShipValid(ship, shipLength));
        }
    }

    /*One length ships are treated like horizontal ones
    * */
    private boolean isShipHorizontal(Ship ship) {
        if (ship.getCoordinates().size() <= 1) return true;
        Iterator<Point> shipCoordinatesIterator = ship.getCoordinates().iterator();
        return shipCoordinatesIterator.next().x != shipCoordinatesIterator.next().x;
    }

    private boolean isHorizontalShipValid(Ship ship, int shipLength) {
        List<Point> coordinates = ship.getCoordinates().stream().sorted(Comparator.comparing(point -> point.x)).
                collect(Collectors.toCollection(ArrayList<Point>::new));

        for (Point point : coordinates) {
            if (checkIfCoordinateIsValid(point)) takenCoordinates.add(point);
            else return false;
        }

        for (int i = 1; i < coordinates.size(); i++) {
            Point previous = coordinates.get(i - 1);
            Point next = coordinates.get(i);
            if (next.x - previous.x != 1) return false;
        }
        return true;
    }

    private boolean isVerticalShipValid(Ship ship, int shipLength) {
        List<Point> coordinates = ship.getCoordinates().stream().sorted(Comparator.comparing(point -> point.y)).
                collect(Collectors.toCollection(ArrayList<Point>::new));

        for (Point point : coordinates) {
            if (checkIfCoordinateIsValid(point)) takenCoordinates.add(point);
            else return false;
        }

        for (int i = 1; i < coordinates.size(); i++) {
            Point previous = coordinates.get(i - 1);
            Point next = coordinates.get(i);
            if (next.y - previous.y != 1) return false;
        }
        return true;
    }

    private boolean checkIfCoordinateIsValid(Point point) {
        return (point.x >= 0 || point.x <BOARD_SIZE || point.y>=0 || point.y <BOARD_SIZE || takenCoordinates.contains(point));
    }
}
