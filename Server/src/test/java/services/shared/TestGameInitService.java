package services.shared;

import configuration.ServerConfiguration;
import gameLogic.Board;
import gameLogic.CellState;
import gameLogic.ShipManager;
import gameLogic.ShipsUtility;
import models.Player;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {ServerConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestGameInitService extends AbstractTestNGSpringContextTests {


    @Autowired
    private ApplicationContext context;

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Autowired
    GameInitService gameInitService;

    @Test
    public void testIfAppropriateBoardInitializes() {

        //given
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ShipsUtility.getSetOf4Ships());
        gameInitService.initGame(Player.FIRST, shipManager);

        //when
        Map<Point, CellState> firstPlayerBoardState = firstPlayerBoard.representCurrentBoardState();
        Map<Point, CellState> secondPlayerBoardState = secondPlayerBoard.representCurrentBoardState();


        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(firstPlayerBoardState).as("FIRST player board state after initialization").isEqualTo(ShipsUtility.getRealBoardStateBeforeShooting());
        sa.assertThat(secondPlayerBoardState).as("SECOND player board should not be initialized").isEmpty();
        sa.assertAll();
    }


}
