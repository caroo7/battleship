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

public class TestHorizontalShipGenerationForValidPositions {
    private ShipGeneratorServiceImpl shipGeneratorService = new ShipGeneratorServiceImpl();

    @BeforeMethod
    void init() {
        shipGeneratorService.ships = new HashSet<>();
        shipGeneratorService.takenShipsCoordinates = new HashSet<>();
    }


    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class,
            dataProvider = "validCoordinatesForFourLengthHorizontalShip")
    void fourLengthHorizontalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 4;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class,
            dataProvider = "validCoordinatesForThreeLengthHorizontalShip")
    void threeLengthHorizontalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 3;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class,
            dataProvider = "validCoordinatesForTwoLengthHorizontalShip")
    void twoLengthHorizontalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 2;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class,
            dataProvider = "validCoordinatesForOneLengthShip")
    void oneLengthHorizontalGenerationTest(Integer x, Integer y) {
        //Arrange
        Point validStartPoint = new Point(x, y);
        int shipLength = 1;

        //Assert
        assertShipAndNeighboursGeneration(validStartPoint, shipLength);
    }


    private void assertShipAndNeighboursGeneration(Point validStartPoint, int shipLength) {
        //Assert
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(shipGeneratorService.generateHorizontalShip(validStartPoint, shipLength));
        sa.assertEquals(getActualGeneratedShipCoordinates(shipGeneratorService.ships), getExpectedShipCoordinates(validStartPoint, shipLength));
        sa.assertEquals(getActualGeneratedShipNeighbours(shipGeneratorService.ships),
                getExpectedShipNeighbours(getActualGeneratedShipCoordinates(shipGeneratorService.ships), validStartPoint));
        sa.assertAll();
    }

    private Set<Point> getExpectedShipCoordinates(Point validStartPoint, int shipLength) {
        Set<Point> coordinates = new HashSet<>();
        for (int x = validStartPoint.x; x < validStartPoint.x + shipLength; x++) {
            coordinates.add(new Point(x, validStartPoint.y));
        }
        return coordinates;
    }

    private Set<Point> getExpectedShipNeighbours(Set<Point> shipCoordinates, Point startPoint) {
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
