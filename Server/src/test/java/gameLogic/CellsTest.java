package gameLogic;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static gameLogic.CellState.*;

public class CellsTest {

    @Test
    public void checkShootedCells() {

        // given
        Cell cellWithShip = Cell.createCellWithShip(createShip());
        Cell emptyCell = Cell.createEmptyCell();

        // when
        CellState stateShootedShip = cellWithShip.shoot();
        CellState stateShootedEmpty = emptyCell.shoot();

        // then
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(stateShootedShip, SHOOTEDSHIP);
        sa.assertEquals(stateShootedEmpty, SHOOTEDEMPTY);
        sa.assertAll();

    }

    @Test
    public void checkCellOfSinkedShip() {

        // given
        Cell cellToBeSunk = Cell.createCellWithShip(createMockSunkShip());

        //when
        CellState sunkCell = cellToBeSunk.shoot();

        //then
        assertEquals(sunkCell, SUNK);
    }

    private Ship createShip() {
        Set<Point> coordinates = new HashSet<>();
        coordinates.add(new Point(0, 0));
        coordinates.add(new Point(0, 1));
        return new Ship(coordinates, null);
    }

    private Ship createMockSunkShip() {
        Ship ship = mock(Ship.class);
        when(ship.isAlive()).thenReturn(false);
        return ship;
    }

}
