package gameLogic;

import org.testng.annotations.Test;

import java.awt.*;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class BoardTest {

    @Test
    public void TestBoardBeforeShooting() {

        //given
        Board board = new Board();
        Set<Ship> ships = prepareSetOfShips();
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ships);
        board.init(shipManager);

        //when
        Map<Point, CellState> boardStateBEFOREShooting = board.representCurrentBoardState();

        //then
        assertEquals(boardStateBEFOREShooting, expectedStateBeforeShooting());
    }

    @Test
    public void TestBoardAfterShooting() {

        //given
        Board board = new Board();
        Set<Ship> ships = prepareSetOfShips();
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ships);
        board.init(shipManager);

        //when
        board.representBoardStateAfterClickingOn(new Point(0, 0));
        board.representBoardStateAfterClickingOn(new Point(0, 1));
        board.representBoardStateAfterClickingOn(new Point(0, 4));
        Map<Point, CellState> boardStateAFTERShooting = board.representBoardStateAfterClickingOn(new Point(3, 3));

        //then
        assertEquals(boardStateAFTERShooting, expectedStateAfterShooting());
    }

    private Set<Ship> prepareSetOfShips() {
        return ShipsUtility.getSetOf4Ships();
    }

    private Map<Point, CellState> expectedStateAfterShooting() {
        return ShipsUtility.getRealBoardStateAfterShootingVar1();

    }

    private Map<Point, CellState> expectedStateBeforeShooting() {
         return ShipsUtility.getRealBoardStateBeforeShooting();
    }
}
