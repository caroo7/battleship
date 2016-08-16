package services.shared.ships;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;

public class TestHorizontalShipGenerationForInvalidPositions {
    private ShipGeneratorServiceImpl shipGeneratorService = new ShipGeneratorServiceImpl();


    @BeforeMethod
    void init() {
        shipGeneratorService.ships = new HashSet<>();
        shipGeneratorService.takenShipsCoordinates = new HashSet<>();
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class, dataProvider = "invalidCoordinatesForFourLengthHorizontalShip")
    void fourLengthHorizontalInvalidGenerationTest(Integer x, Integer y) {
        //Arrange
        Point invalidStartPoint = new Point(x, y);
        int shipLength = 4;

        //Assert
         assertShipWasNotGenerated(invalidStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class, dataProvider = "invalidCoordinatesForThreeLengthHorizontalShip")
    void threeLengthHorizontalInvalidGenerationTest(Integer x, Integer y) {
        //Arrange
        Point invalidStartPoint = new Point(x, y);
        int shipLength = 3;

        //Assert
        assertShipWasNotGenerated(invalidStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class, dataProvider = "invalidCoordinatesForTwoLengthHorizontalShip")
    void twoLengthHorizontalInvalidGenerationTest(Integer x, Integer y) {
        //Arrange
        Point invalidStartPoint = new Point(x, y);
        int shipLength = 2;

        //Assert
        assertShipWasNotGenerated(invalidStartPoint, shipLength);
    }

    @Test(dataProviderClass = DataProvidersForHorizontalShipGeneration.class, dataProvider = "invalidCoordinatesForOneLengthShip")
    void oneLengtInvalidGenerationTest(Integer x, Integer y) {
        //Arrange
        Point invalidStartPoint = new Point(x, y);
        int shipLength = 1;


        //Assert
        assertShipWasNotGenerated(invalidStartPoint, shipLength);
    }

    private void assertShipWasNotGenerated(Point invalidStartPoint, int shipLength) {
        //Assert
        SoftAssert sa = new SoftAssert();
        sa.assertFalse(shipGeneratorService.generateVerticalShip(invalidStartPoint, shipLength));
        sa.assertEquals(shipGeneratorService.ships, Collections.EMPTY_SET);
        sa.assertAll();
    }
}
