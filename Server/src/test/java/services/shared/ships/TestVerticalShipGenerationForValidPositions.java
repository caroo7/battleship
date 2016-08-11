package services.shared.ships;

import configuration.Config;
import gameLogic.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class TestVerticalShipGenerationForValidPositions {
    private ShipGeneratorServiceImpl shipGeneratorService = new ShipGeneratorServiceImpl();

    @BeforeMethod
    void init() {
        shipGeneratorService.ships = new HashSet<>();
        shipGeneratorService.takenShipsCoordinates = new HashSet<>();
    }

    @Test(dataProviderClass = DataProvidersForVerticalShipGeneration.class,
            dataProvider = "validCoordinatesForFourLengthVerticalShip")
    void fourLengtthVerticalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 4;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }


    @Test(dataProviderClass = DataProvidersForVerticalShipGeneration.class,
            dataProvider = "validCoordinatesForThreeLengthVerticalShip")
    void threeLengtthVerticalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 3;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }


    @Test(dataProviderClass = DataProvidersForVerticalShipGeneration.class,
            dataProvider = "validCoordinatesForTwoLengthVerticalShip")
    void twoLengtthVerticalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 2;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForVerticalShipGeneration.class, dataProvider = "validCoordinatesForOneLengthShip")
    void oneLengthVerticalValidGenerationTest(Integer x,Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 1;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }


    private void assertShipAndNeighboursGeneration(Point validStartPoint, int shipLength) {
        //Assert
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(shipGeneratorService.generateVerticalShip(validStartPoint, shipLength));
        sa.assertEquals(getActualGeneratedShipCoordinates(shipGeneratorService.ships), getExpectedShipCoordinates(validStartPoint, shipLength));
        sa.assertEquals(getActualGeneratedShipNeighbours(shipGeneratorService.ships),
                getExpectedShipNeighbours(getActualGeneratedShipCoordinates(shipGeneratorService.ships), validStartPoint));
        sa.assertAll();
    }


    private Set<Point> getExpectedShipCoordinates(Point validStartPoint, int shipLength) {
        Set<Point> coordinates = new HashSet<>();
        for (int y = validStartPoint.y; y < validStartPoint.y + shipLength; y++) {
            coordinates.add(new Point(validStartPoint.x, y));
        }
        return coordinates;
    }

    private Set<Point> getExpectedShipNeighbours(Set<Point> shipCoordinates, Point startPoint) {
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

    private boolean validatePoint(int x, int y) {
        return !(x < 0 || x >= Config.BOARD_SIZE) && !(y < 0 || y >= Config.BOARD_SIZE);
    }


    private Set<Point> getActualGeneratedShipCoordinates(Set<Ship> ships) {
        Iterator<Ship> shipIterator = ships.iterator();
        return shipIterator.hasNext() ? shipIterator.next().getCoordinates() : Collections.EMPTY_SET;
    }

    private Set<Point> getActualGeneratedShipNeighbours(Set<Ship> ships) {
        Iterator<Ship> shipIterator = ships.iterator();
        return shipIterator.hasNext() ? shipIterator.next().getNeighbours() : Collections.EMPTY_SET;
    }
}
