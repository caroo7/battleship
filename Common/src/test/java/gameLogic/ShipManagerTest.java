package gameLogic;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class ShipManagerTest {

    ShipManager shipManager = new ShipManager();

    @BeforeMethod
    private void initShips() {
        shipManager.initShips(prepareSetOsShips());
    }

    @Test
    public void testAmountOfAliveShipsBEFOREShooting() {

        // given BeforeMethod

        // when
        long aliveShipsBEFOREShooting = shipManager.getAmountOfLeftShips();

        //then
        assertEquals(aliveShipsBEFOREShooting, 2);
    }


    @Test
    public void testAmountOfAliveShipsAFTERShooting() {

        // given
        Ship toLinkWithCells = shipManager.getAllShips().stream().findFirst().get();
        Cell toSunk1 = Cell.createCellWithShip(toLinkWithCells);
        Cell toSunk2 = Cell.createCellWithShip(toLinkWithCells);

        // when
        toSunk1.shoot();
        toSunk2.shoot();
        long aliveShipsAFTERShooting = shipManager.getAmountOfLeftShips();

        //then
        assertEquals(aliveShipsAFTERShooting, 1);
    }

    @Test
    public void testPointsOfSunkShip() {

        // given
        List<Ship> cellsShip = shipManager.getAllShips().stream().collect(Collectors.toList());
        Ship toLinkWithCells = cellsShip.get(0);
        Cell toSunk1 = Cell.createCellWithShip(toLinkWithCells);
        Cell toSunk2 = Cell.createCellWithShip(toLinkWithCells);

        // when
        toSunk1.shoot();
        toSunk2.shoot();
        Set<Point> sunkCellsPoints = shipManager.getPointsOfSunkShip(new Point(0, 0));

        //then
        assertEquals(sunkCellsPoints, expectedSetOfPoints());
    }

    private Set<Point> expectedSetOfPoints() {
        Set<Point> cellsPoints = new HashSet<>();
        cellsPoints.add(new Point(0, 0));
        cellsPoints.add(new Point(0, 1));
        return cellsPoints;
    }

    private Set<Ship> prepareSetOsShips() {
        Set<Ship> ships = new HashSet<>();
        ships.add(ShipsUtility.createShipWithoutNeighbours(new Point(0, 0), new Point(0, 1)));
        ships.add(ShipsUtility.createShipWithoutNeighbours(new Point(2, 1), new Point(2, 2)));
        return ships;
    }

}
