package services.shared.board;

import configuration.ServerConfiguration;
import gameLogic.Board;
import gameLogic.ShipManager;
import gameLogic.ShipsUtility;
import models.BoardsMessage;
import models.GameState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.shared.BoardsMessageService;
import services.shared.PlayerRegistrationService;
import services.shared.ShootService;
import services.undisclosed.ActualPlayerServiceImpl;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;


@ContextConfiguration(classes = {ServerConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestBoardMessageService extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private BoardsMessageService boardsMessageService;

    @Autowired
    private ShootService shootService;

    @Autowired
    private Board firstPlayerBoard;

    @Autowired
    private Board secondPlayerBoard;

    @Autowired
    private ActualPlayerServiceImpl actualPlayerService;

    @Autowired
    private PlayerRegistrationService registrationService;

    @BeforeMethod
    public void prepareBoardsMessageService() throws Exception {
        registrationService.registerPlayer();
        firstPlayerBoard.init(createShipManagerForBoard());

        registrationService.registerPlayer();
        secondPlayerBoard.init(createShipManagerForBoard());

        actualPlayerService.setActualPlayerAsFirstPlayerAtStart();
    }

    private ShipManager createShipManagerForBoard() {
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ShipsUtility.getSetOf4Ships());
        return shipManager;
    }

    @Test
    public void testTheMessageBeforeShooting() throws Exception {

        //given BeforeMethod

        //when
        BoardsMessage boardsMessage = boardsMessageService.retrieveDataForUser(Player.FIRST);

        //then
        //TODO: soft assert
        assertEquals(boardsMessage.isGameAvailable(), true);
        assertEquals(boardsMessage.getUserGameState(), GameState.YouCanPlay);
        assertEquals(boardsMessage.getActualRivalBoardState(), ShipsUtility.createEmptyBoard());
        assertEquals(boardsMessage.getActualUserBoardStates(), ShipsUtility.getRealBoardStateBeforeShooting());
        assertEquals(boardsMessage.getRivalShipsLeft(), 4);
    }

    @Test
    public void testTheMessageAfterShooting() throws Exception {

        //given
        // first player
        shootService.shootOn(new Point(0, 0));
        shootService.shootOn(new Point(0, 1));
        shootService.shootOn(new Point(0, 4));
        shootService.shootOn(new Point(3, 3));
        //second player
        shootService.shootOn(new Point(6, 2));

        //when
        BoardsMessage boardsMessage1 = boardsMessageService.retrieveDataForUser(Player.FIRST);
        BoardsMessage boardsMessage2 = boardsMessageService.retrieveDataForUser(Player.SECOND);

        //then
        SoftAssert sa = new SoftAssert();
        // first player
        //TODO: consider assertJ or your own custom assertion at least
        assertThat(boardsMessage1.getActualRivalBoardState()).as("board 1 actual rival board is not null").isNotEmpty();

        sa.assertEquals(boardsMessage1.isGameAvailable(), true, "game availability is wrong for First player");
        sa.assertEquals(boardsMessage1.getUserGameState(), GameState.NotYourTurn, "player's turn is wrong for First player");
        sa.assertEquals(boardsMessage1.getActualRivalBoardState(), ShipsUtility.getRepresentationOfBoardStateAfterShootingVar1(), "Wrong representation of rival's board for First Player");
        sa.assertEquals(boardsMessage1.getActualUserBoardStates(), ShipsUtility.getRealBoardStateAfterShootingVar2(), "wrong REAL board state for First player");
        sa.assertEquals(boardsMessage1.getRivalShipsLeft(), 3, "wrong amount of alive rival ships for First Player");
        // second player
        sa.assertEquals(boardsMessage2.isGameAvailable(), true, "game availability is wrong for Second player");
        sa.assertEquals(boardsMessage2.getUserGameState(), GameState.YouCanPlay, "player's turn is wrong for Second player");
        sa.assertEquals(boardsMessage2.getActualRivalBoardState(), ShipsUtility.getRepresentationOfBoardStateAfterShootingVar2(), "Wrong representation of rival's board for Second Player");
        sa.assertEquals(boardsMessage2.getActualUserBoardStates(), ShipsUtility.getRealBoardStateAfterShootingVar1(), "wrong REAL board state for Second player");
        sa.assertEquals(boardsMessage2.getRivalShipsLeft(), 3, "wrong amount of alive rival ships for Second Player");
        sa.assertAll();
    }

}