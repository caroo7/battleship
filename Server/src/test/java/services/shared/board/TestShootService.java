package services.shared.board;

import gameLogic.Board;
import gameLogic.ShipManager;
import gameLogic.ShipsUtilityOnlyForTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.undisclosed.ActualPlayerServiceImpl;

import java.awt.*;

import static org.mockito.Mockito.*;

public class TestShootService {

    private ShootServiceImpl shootService;
    private ActualPlayerServiceImpl actualPlayerService;

    @BeforeMethod
    private void prepareShootService() {
        shootService = new ShootServiceImpl();
        actualPlayerService = mock(ActualPlayerServiceImpl.class);
        shootService.actualPlayerService = actualPlayerService;
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ShipsUtilityOnlyForTests.getSetOf4Ships());
        Board board = new Board();
        board.init(shipManager);
        shootService.firstPlayerBoard = board;
        shootService.secondPlayerBoard = board;
    }

    @Test
    public void testShootOnShip() {

        //given BeforeMethod

        //when
        shootService.shootOn(new Point(0, 0));
        shootService.shootOn(new Point(0, 1));

        //then
        verify(actualPlayerService, times(0)).changeActualPlayer();
    }

    @Test
    public void testShootOnEmpty() {

        //given BeforeMethod

        //when
        shootService.shootOn(new Point(5, 0));
        shootService.shootOn(new Point(0, 5));

        //then
        verify(actualPlayerService, times(2)).changeActualPlayer();
    }

    @DataProvider
    private Object[][] volleyOfShotsDataProvider() {
        return new Object[][]{{2}, {3}, {4}};
    }

    @Test(dataProvider = "volleyOfShotsDataProvider")
    public void volleyOfShotsTest(int shotsNumber) {
        // act
        shootService.randomShoot(shotsNumber);

        // assert
        verify(actualPlayerService, times(1)).changeActualPlayer();
    }

}