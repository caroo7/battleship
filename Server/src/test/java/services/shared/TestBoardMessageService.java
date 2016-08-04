package services.shared;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.undisclosed.ActualPlayerService;
import services.undisclosed.AliveShipsService;
import services.undisclosed.BoardStateService;
import services.undisclosed.GameAvailableService;

import java.awt.*;

import static org.testng.Assert.assertEquals;


@ContextConfiguration(classes = {ServerConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestBoardMessageService extends AbstractTestNGSpringContextTests {


    BoardsMessageService boardsMessageService;
    ShootService shootService;

    @Autowired
    ApplicationContext context;

    @BeforeMethod
    public void prepareBoardsMessageService() throws Exception {

        ShipManager firstShipManager = new ShipManager();
        firstShipManager.initShips(ShipsUtility.getSetOf4Ships());
        Board firstPlayerBoard = (Board) context.getBean("firstPlayerBoard");
        firstPlayerBoard.init(firstShipManager);

        ShipManager secondShipManager = new ShipManager();
        secondShipManager.initShips(ShipsUtility.getSetOf4Ships());
        Board secondPlayerBoard = (Board) context.getBean("secondPlayerBoard");
        secondPlayerBoard.init(secondShipManager);

        ActualPlayerService actualPlayerService = (ActualPlayerService) context.getBean("actualPlayerService");
        actualPlayerService.setActualPlayerAsFirstPlayerAtStart();

        PlayerRegistrationService playerRegistrationService = (PlayerRegistrationService) context.getBean("playerRegistrationService");
        playerRegistrationService.registerPlayer();
        playerRegistrationService.registerPlayer();

        GameAvailableService gameAvailableService = (GameAvailableService) context.getBean("gameAvailableService");
        BoardStateService boardStateService = (BoardStateService) context.getBean("boardStateService");
        AliveShipsService aliveShipsService = (AliveShipsService) context.getBean("aliveShipsService");
        this.boardsMessageService = (BoardsMessageService) context.getBean("boardMessageService");

        this.shootService = (ShootService) context.getBean("shootService");
    }


    @Test
    public void testTheMessageBeforeShooting() throws Exception {

        //given BeforeMethod

        //when
        BoardsMessage boardsMessage = boardsMessageService.retrieveDataForUser(Player.FIRST);

        //then
        assertEquals(boardsMessage.isGameAvailable(), true);
        assertEquals(boardsMessage.getGameState(), GameState.YourTurn);
        assertEquals(boardsMessage.getActualRivalBoardState(), ShipsUtility.getBoardStateWith4ShipsBeforeShooting());
        assertEquals(boardsMessage.getActualUserBoardStates(), ShipsUtility.getBoardStateWith4ShipsBeforeShooting());
        assertEquals(boardsMessage.getRivalShipsLeft(), 4);


    }

    @Test
    public void testTheMessageAfterShooting() throws Exception {

        //given
        shootService.shootOn(new Point(0, 0));
        shootService.shootOn(new Point(0, 1));
        shootService.shootOn(new Point(0, 4));
        shootService.shootOn(new Point(3, 3));

        //when
        BoardsMessage boardsMessage = boardsMessageService.retrieveDataForUser(Player.FIRST);

        //then
        assertEquals(boardsMessage.isGameAvailable(), true);
        assertEquals(boardsMessage.getGameState(), GameState.NotYourTurn);
        assertEquals(boardsMessage.getActualRivalBoardState(), ShipsUtility.getBoardStateWith4ShipsAfterShooting());
        assertEquals(boardsMessage.getActualUserBoardStates(), ShipsUtility.getBoardStateWith4ShipsBeforeShooting());
        assertEquals(boardsMessage.getRivalShipsLeft(),3);
    }

}
