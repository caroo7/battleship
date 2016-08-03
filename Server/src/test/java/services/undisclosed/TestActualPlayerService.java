package services.undisclosed;

import models.Player;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestActualPlayerService {

    @Test
    public void testPlayersOrder() {

        //given
        ActualPlayerService actualPlayerService = new ActualPlayerServiceImpl();

        //when
        actualPlayerService.setActualPlayerAsFirstPlayerAtStart();
        Player player1 = actualPlayerService.getActualPlayer();
        actualPlayerService.changeActualPlayer();
        Player player2 = actualPlayerService.getActualPlayer();
        actualPlayerService.changeActualPlayer();
        Player player3 = actualPlayerService.getActualPlayer();

        //then
        assertEquals(player1, Player.FIRST);
        assertEquals(player2, Player.SECOND);
        assertEquals(player3, Player.FIRST);

    }
}
