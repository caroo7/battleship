package models;

import gameLogic.CellState;
import gameLogic.ShipsUtility;
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
                ShipsUtility.getRealBoardStateBeforeShooting(),
                ShipsUtility.getRealBoardStateBeforeShooting(),
                4);

        //when
        boolean isGameAvailable = boardsMessage.isGameAvailable();
        GameState gameState = boardsMessage.getUserGameState();
        Map<Point, CellState> rivalBoardState = boardsMessage.getActualRivalBoardState();
        Map<Point, CellState> userBoardState = boardsMessage.getActualUserBoardStates();

        //then
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(isGameAvailable);
        sa.assertEquals(gameState, GameState.YouArePlaying);
        sa.assertEquals(rivalBoardState, ShipsUtility.getRealBoardStateBeforeShooting());
        sa.assertEquals(userBoardState, ShipsUtility.getRealBoardStateBeforeShooting());
        sa.assertAll();
    }
}
