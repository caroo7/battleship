package services.undisclosed;

import gameLogic.Board;
import gameLogic.ShipManager;
import gameLogic.ShipsUtility;
import models.Player;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.Assert.assertEquals;

public class TestAliveShipsService {

    @Test
    public void testAmountOfAliveShips() throws Exception {

        //given
        Board firstPlayerboard = prepareBoardWith4AliveShips();

        Board secondPlayerboard = prepareBoardWith4AliveShips();
        secondPlayerboard.representBoardStateAfterClickingOn(new Point(0, 0));
        secondPlayerboard.representBoardStateAfterClickingOn(new Point(0, 1));
        secondPlayerboard.representBoardStateAfterClickingOn(new Point(0, 4));
        secondPlayerboard.representBoardStateAfterClickingOn(new Point(3, 3));

        AliveShipsServiceImpl aliveShipsService = new AliveShipsServiceImpl();
        aliveShipsService.firstPlayerBoard = firstPlayerboard;
        aliveShipsService.secondPlayerBoard = secondPlayerboard;

        //when
        long amountOfFirstPlayerShips = aliveShipsService.getRivalAliveAmountOfShips(Player.SECOND);
        long amountOfSecondPlayerShips = aliveShipsService.getRivalAliveAmountOfShips(Player.FIRST);

        //then
        assertEquals(amountOfFirstPlayerShips, 4);
        assertEquals(amountOfSecondPlayerShips, 3);
    }

    @Test(expectedExceptions = Exception.class)
    public void testExeptionNullShipManager() throws Exception {

        //given
        Board board = new Board();
        AliveShipsServiceImpl aliveShipsService = new AliveShipsServiceImpl();

        //when
        aliveShipsService.getRivalAliveAmountOfShips(Player.SECOND);

    }

    private Board prepareBoardWith4AliveShips() {
        Board board = new Board();
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ShipsUtility.getSetOf4Ships());
        board.init(shipManager);
        return board;
    }
}
