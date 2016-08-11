package models;

import gameLogic.CellState;

import gameLogic.ShipsUtilityOnlyForTests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.Map;

public class BoardMessageTest {

    @Test
    public void testMessageObject() {

        //given
        BoardsMessage boardsMessage = new BoardsMessage(true,
                GameState.YouArePlaying,
                ShipsUtilityOnlyForTests.getRealBoardStateBeforeShooting(),
                ShipsUtilityOnlyForTests.getRealBoardStateBeforeShooting(),
                4);

        //when
        boolean isGameAvailable = boardsMessage.isGameAvailable();
        GameState gameState = boardsMessage.getUserGameState();
        Map<Point, CellState> rivalBoardState = boardsMessage.getActualRivalBoardState();
        Map<Point, CellState> userBoardState = boardsMessage.getActualUserBoardStates();
        long aliveShips = boardsMessage.getRivalShipsLeft();

        //then
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(isGameAvailable);
        sa.assertEquals(gameState, GameState.YouArePlaying);
        sa.assertEquals(rivalBoardState, ShipsUtilityOnlyForTests.getRealBoardStateBeforeShooting());
        sa.assertEquals(userBoardState, ShipsUtilityOnlyForTests.getRealBoardStateBeforeShooting());
        sa.assertEquals(aliveShips,4);
        sa.assertAll();
    }
}
