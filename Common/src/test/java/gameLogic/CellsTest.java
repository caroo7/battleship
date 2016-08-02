package gameLogic;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static gameLogic.CellState.*;

public class CellsTest {

    @Test
    public void checkShootedCells() {

        // given
        Cell cellWithShip = Cell.createCellWithShip(ShipsUtility.createShipWithoutNeighbours(new Point(0,0), new Point(0,1)));
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
    public void checkCellOfSunkShip() {

        // given
        Cell cellToBeSunk = Cell.createCellWithShip(createMockSunkShip());

        // when
        CellState sunkCell = cellToBeSunk.shoot();

        // then
        assertEquals(sunkCell, SUNK);
    }



    private Ship createMockSunkShip() {
        Ship ship = mock(Ship.class);
        when(ship.isAlive()).thenReturn(false);
        return ship;
    }

}
