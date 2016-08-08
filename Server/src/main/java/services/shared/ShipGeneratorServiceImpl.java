package services.shared;

import gameLogic.Ship;
import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Responsible for set of ships generation. This set will be used in future during the game.
 */
public class ShipGeneratorServiceImpl implements ShipGeneratorService {

    /**
     * Generated ships
     */
    private Set<Ship> ships;

    /**
     * Places where we cannot generate ships (because of already existed ships)
     */
    private Set<Point> invalidShipsCoordinates;

    private Random random = new Random();

    /**
     * Board edge size (e.g. 8 means that board has 8x8 size)
     */
    private static final int BOARD_SIZE = 8;

    /**
     * It is used during generation phase, before game starts. Creates hardcoded numbers of horizontal and vertical ships randomly.
     * Also add information about places around generated ship (neighbours). It ensures that there will be no ships which adhere together.
     * If ship lays on the border of the board it doesn't allow to add to neighbours points out of the board.
     * @return created set of ships
     */
    @Override
    public Set<Ship> generateShips() {
        ships = new HashSet<>();
        invalidShipsCoordinates = new HashSet<>();
        generateShip(4);
        generateShip(3);
        generateShip(2);
        generateShip(2);
        generateShip(2);
        generateShip(1);
        generateShip(1);
        return Collections.unmodifiableSet(ships);
    }


    private void generateShip(int shipLenght) {
        Point point;
        do {
            point = new Point(random.nextInt(BOARD_SIZE), random.nextInt(BOARD_SIZE));
        }
        while (!(random.nextInt(2) == 1 ? generateVerticalShip(point, shipLenght) : generateHorizontalShip(point, shipLenght)));

    }


    private boolean generateVerticalShip(Point startPoint, int shipLength) {
        Point checkCoordinate;
        Set<Point> shipCoordinates = new HashSet<>();

        for (int i = startPoint.y; i < startPoint.y + shipLength; i++) {
            if (i >= BOARD_SIZE) return false;
            checkCoordinate = new Point(startPoint.x, i);
            if (invalidShipsCoordinates.contains(checkCoordinate)) return false;
            else shipCoordinates.add(checkCoordinate);
        }
        Set<Point> neighbours = generateVerticalShipNeighbours(shipCoordinates, startPoint);

        invalidShipsCoordinates.addAll(shipCoordinates);
        invalidShipsCoordinates.addAll(neighbours);

        ships.add(new Ship(shipCoordinates, neighbours));
        return true;
    }

    private Set<Point> generateVerticalShipNeighbours(Set<Point> shipCoordinates, Point startPoint) {
        Set<Point> neighboursCoordinates = new HashSet<>();

        if (validatePoint(startPoint.x, startPoint.y - 1)) {
            neighboursCoordinates.add(new Point(startPoint.x, startPoint.y - 1));
        }
        if (validatePoint(startPoint.x, startPoint.y + shipCoordinates.size())) {
            neighboursCoordinates.add(new Point(startPoint.x, startPoint.y + shipCoordinates.size()));
        }

        Set<Point> neighboursCoordinatesCopy = new HashSet<>(neighboursCoordinates);
        neighboursCoordinatesCopy.addAll(shipCoordinates);

        neighboursCoordinatesCopy.stream().forEach(point -> {
            if (validatePoint(point.x - 1, point.y)) {
                neighboursCoordinates.add(new Point(point.x - 1, point.y));
            }
            if (validatePoint(point.x + 1, point.y)) {
                neighboursCoordinates.add(new Point(point.x + 1, point.y));
            }
        });

        return neighboursCoordinates;
    }

    private boolean generateHorizontalShip(Point startPoint, int shipLength) {
        Point checkCoordinate;
        Set<Point> shipCoordinates = new HashSet<>();

        for (int i = startPoint.x; i < startPoint.x + shipLength; i++) {
            if (i >= BOARD_SIZE) return false;
            checkCoordinate = new Point(i, startPoint.y);
            if (invalidShipsCoordinates.contains(checkCoordinate)) return false;
            else shipCoordinates.add(checkCoordinate);
        }

        Set<Point> neighbours = generateHorizontalShipNeighbours(shipCoordinates, startPoint);

        invalidShipsCoordinates.addAll(shipCoordinates);
        invalidShipsCoordinates.addAll(neighbours);

        ships.add(new Ship(shipCoordinates, neighbours));
        return true;
    }

    private Set<Point> generateHorizontalShipNeighbours(Set<Point> shipCoordinates, Point startPoint) {
        Set<Point> neighboursCoordinates = new HashSet<>();
        if (validatePoint(startPoint.x - 1, startPoint.y)) {
            neighboursCoordinates.add(new Point(startPoint.x - 1, startPoint.y));
        }
        if (validatePoint(startPoint.x + shipCoordinates.size(), startPoint.y)) {
            neighboursCoordinates.add(new Point(startPoint.x + shipCoordinates.size(), startPoint.y));
        }

        Set<Point> neighboursCoordinatesCopy = new HashSet<>(neighboursCoordinates);
        neighboursCoordinatesCopy.addAll(shipCoordinates);

        neighboursCoordinatesCopy.stream().forEach(point -> {
            if (validatePoint(point.x, point.y - 1)) {
                neighboursCoordinates.add(new Point(point.x, point.y - 1));
            }
            if (validatePoint(point.x, point.y + 1)) {
                neighboursCoordinates.add(new Point(point.x, point.y + 1));
            }
        });

        return neighboursCoordinates;
    }

    private boolean validatePoint(int x, int y) {
        return !(x < 0 || x >= BOARD_SIZE) && !(y < 0 || y >= BOARD_SIZE);
    }

}