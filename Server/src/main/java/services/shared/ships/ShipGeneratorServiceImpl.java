package services.shared.ships;

import gameLogic.Ship;
import services.shared.ShipGeneratorService;

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
    Set<Ship> ships;

    /**
     * Places where we cannot generate ships (because of already existed ships)
     */
    Set<Point> takenShipsCoordinates;

    final Random random = new Random();

    /**
     * Board edge size (e.g. 8 means that board has 8x8 size)
     */
    static final int BOARD_SIZE = 8;

    /**
     * It is used during generation phase, before game starts. Creates hardcoded numbers of horizontal and vertical ships randomly.
     * Also add information about places around generated ship (neighbours). It ensures that there will be no ships which adhere together.
     * If ship lays on the border of the board it doesn't allow to add to neighbours points out of the board.
     *
     * @return created set of ships
     */
    @Override
    public Set<Ship> generateShips() {
        ships = new HashSet<>();
        takenShipsCoordinates = new HashSet<>();
        generateShip(4);
        generateShip(3);
        generateShip(2);
        generateShip(2);
        generateShip(2);
        generateShip(1);
        generateShip(1);
        return Collections.unmodifiableSet(ships);
    }


    private void generateShip(int shipLength) {
        Point point;
        do {
            point = new Point(random.nextInt(BOARD_SIZE), random.nextInt(BOARD_SIZE));
        }
        while (!(random.nextInt(2) == 1 ? generateVerticalShip(point, shipLength) : generateHorizontalShip(point, shipLength)));
    }


    boolean generateVerticalShip(Point startPoint, int shipLength) {
        if (validatePoint(startPoint.x, startPoint.y)) {
            Point checkCoordinate;
            Set<Point> shipCoordinates = new HashSet<>();

            for (int i = startPoint.y; i < startPoint.y + shipLength; i++) {
                if (i >= BOARD_SIZE) return false;
                checkCoordinate = new Point(startPoint.x, i);
                if (takenShipsCoordinates.contains(checkCoordinate)) return false;
                else shipCoordinates.add(checkCoordinate);
            }
            Set<Point> neighbours = generateVerticalShipNeighbours(shipCoordinates, startPoint);

            takenShipsCoordinates.addAll(shipCoordinates);
            takenShipsCoordinates.addAll(neighbours);

            ships.add(new Ship(shipCoordinates, neighbours));
            return true;
        }
        return false;
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

    boolean generateHorizontalShip(Point startPoint, int shipLength) {
        if (!validatePoint(startPoint.x, startPoint.y)) return false;
        Point checkCoordinate;
        Set<Point> shipCoordinates = new HashSet<>();

        for (int i = startPoint.x; i < startPoint.x + shipLength; i++) {
            if (i >= BOARD_SIZE) return false;
            checkCoordinate = new Point(i, startPoint.y);
            if (takenShipsCoordinates.contains(checkCoordinate)) return false;
            else shipCoordinates.add(checkCoordinate);
        }

        Set<Point> neighbours = generateHorizontalShipNeighbours(shipCoordinates, startPoint);

        takenShipsCoordinates.addAll(shipCoordinates);
        takenShipsCoordinates.addAll(neighbours);

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