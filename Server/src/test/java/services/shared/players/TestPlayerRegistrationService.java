package services.shared.players;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.shared.players.PlayerRegistrationServiceImpl;
import services.undisclosed.ActualPlayerServiceImpl;

public class TestPlayerRegistrationService {

    //TODO parametrized testing

    @Test(expectedExceptions = Exception.class)
    public void testIfItsPossibleToAddMoreThenTwoPlayers() throws Exception {

        //given
        PlayerRegistrationServiceImpl playerRegistrationService = new PlayerRegistrationServiceImpl();
        playerRegistrationService.actualPlayerService = new ActualPlayerServiceImpl();

        //when
        playerRegistrationService.registerPlayer();
        playerRegistrationService.registerPlayer();
        playerRegistrationService.registerPlayer();
    }

    @Test
    public void testRegistrationAndUnregistration() throws Exception {

        //given
        PlayerRegistrationServiceImpl playerRegistrationService = new PlayerRegistrationServiceImpl();
        playerRegistrationService.actualPlayerService = new ActualPlayerServiceImpl();

        //when
        playerRegistrationService.registerPlayer();
        int playerNumbers1 = playerRegistrationService.getConnectedPlayersNumber();
        playerRegistrationService.registerPlayer();
        int playerNumbers2 = playerRegistrationService.getConnectedPlayersNumber();
        playerRegistrationService.unregisterPlayer();
        int playerNumbers3 = playerRegistrationService.getConnectedPlayersNumber();
        playerRegistrationService.registerPlayer();
        int playerNumbers4 = playerRegistrationService.getConnectedPlayersNumber();
        playerRegistrationService.unregisterBothPlayers();
        int playerNumbers5 = playerRegistrationService.getConnectedPlayersNumber();

        //then
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(playerNumbers1, 1, "one player should be registered");
        sa.assertEquals(playerNumbers2, 2, "two players should be registered");
        sa.assertEquals(playerNumbers3, 1, "one player should be left after unregistered");
        sa.assertEquals(playerNumbers4, 2, "two players after second round of registration");
        sa.assertEquals(playerNumbers5, 0, "all players are unregistered now");
        sa.assertAll();
    }

}
