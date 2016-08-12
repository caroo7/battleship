package services.undisclosed;

import gameLogic.Board;
import gameLogic.ShipManager;
import gameLogic.ShipsUtilityOnlyForTests;
import models.Player;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.Assert.assertEquals;

public class TestAliveShipsService {

    @Test
    public void testAmountOfAliveShips() throws Exception {

        //given
        Board firstPlayerBoard = prepareBoardWith4AliveShips();

        Board secondPlayerBoard = prepareBoardWith4AliveShips();
        secondPlayerBoard.representBoardStateAfterClickingOn(new Point(0, 0));
        secondPlayerBoard.representBoardStateAfterClickingOn(new Point(0, 1));
        secondPlayerBoard.representBoardStateAfterClickingOn(new Point(0, 4));
        secondPlayerBoard.representBoardStateAfterClickingOn(new Point(3, 3));

        AliveShipsServiceImpl aliveShipsService = new AliveShipsServiceImpl();
        aliveShipsService.firstPlayerBoard = firstPlayerBoard;
        aliveShipsService.secondPlayerBoard = secondPlayerBoard;

        //when
        long amountOfFirstPlayerShips = aliveShipsService.getRivalAliveAmountOfShips(Player.SECOND);
        long amountOfSecondPlayerShips = aliveShipsService.getRivalAliveAmountOfShips(Player.FIRST);

        //then
        assertEquals(amountOfFirstPlayerShips, 4);
        assertEquals(amountOfSecondPlayerShips, 3);
    }

    @Test(expectedExceptions = Exception.class)
    public void testExceptionNullShipManager() throws Exception {

        //given
        AliveShipsServiceImpl aliveShipsService = new AliveShipsServiceImpl();

        //when
        aliveShipsService.getRivalAliveAmountOfShips(Player.SECOND);

    }

    private Board prepareBoardWith4AliveShips() {
        Board board = new Board();
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ShipsUtilityOnlyForTests.getSetOf4Ships());
        board.init(shipManager);
        return board;
    }
}
