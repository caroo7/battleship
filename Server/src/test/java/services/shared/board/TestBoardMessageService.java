package services.shared.board;

import configuration.ServerConfiguration;
import gameLogic.Board;
import gameLogic.ShipManager;
import gameLogic.ShipsUtilityOnlyForTests;
import models.BoardsMessage;
import models.GameState;
import models.Player;
import org.assertj.core.api.SoftAssertions;
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
        shipManager.initShips(ShipsUtilityOnlyForTests.getSetOf4Ships());
        return shipManager;
    }

    @Test
    public void testTheMessageBeforeShooting() throws Exception {

        //given BeforeMethod

        //when
        BoardsMessage boardsMessage = boardsMessageService.retrieveDataForUser(Player.FIRST);

        //then
        SoftAssert sa =new SoftAssert();
        sa.assertEquals(boardsMessage.isGameAvailable(), true);
        sa.assertEquals(boardsMessage.getUserGameState(), GameState.YouCanPlay);
        sa.assertEquals(boardsMessage.getActualRivalBoardState(), ShipsUtilityOnlyForTests.createEmptyBoard());
        sa.assertEquals(boardsMessage.getActualUserBoardStates(), ShipsUtilityOnlyForTests.getRealBoardStateBeforeShooting());
        sa.assertEquals(boardsMessage.getRivalShipsLeft(), 4);
        sa.assertAll();
    }

    @Test
    public void testTheMessageAfterShooting() throws Exception {

        //given BeforeMethod and
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
        SoftAssertions sa = new SoftAssertions();
        // first player
        sa.assertThat(boardsMessage1.isGameAvailable()).as("Is game available for FIRST player").isTrue();
        sa.assertThat(boardsMessage1.getActualRivalBoardState()).as("rival board for player FIRST").isEqualTo(ShipsUtilityOnlyForTests.getRepresentationOfBoardStateAfterShootingVar1());
        sa.assertThat(boardsMessage1.getUserGameState()).as("game state foe FIRST player").isEqualTo(GameState.NotYourTurn);
        sa.assertThat(boardsMessage1.getActualUserBoardStates()).as("user board state for FIRST player").isEqualTo(ShipsUtilityOnlyForTests.getRealBoardStateAfterShootingVar2());
        sa.assertThat(boardsMessage1.getRivalShipsLeft()).as("amount of alive rival ships for First Player").isEqualTo(3);
        // second player
        sa.assertThat(boardsMessage2.isGameAvailable()).as("Is game available for SECOND player").isTrue();
        sa.assertThat(boardsMessage2.getActualRivalBoardState()).as("rival board for player SECOND").isEqualTo(ShipsUtilityOnlyForTests.getRepresentationOfBoardStateAfterShootingVar2());
        sa.assertThat(boardsMessage2.getUserGameState()).as("game state foe SECOND player").isEqualTo(GameState.YouCanPlay);
        sa.assertThat(boardsMessage2.getActualUserBoardStates()).as("user board state for SECOND player").isEqualTo(ShipsUtilityOnlyForTests.getRealBoardStateAfterShootingVar1());
        sa.assertThat(boardsMessage2.getRivalShipsLeft()).as("amount of alive rival ships for First Player").isEqualTo(3);
        sa.assertAll();
    }

}