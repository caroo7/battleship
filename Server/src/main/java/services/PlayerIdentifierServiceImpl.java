package services;

import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerIdentifierServiceImpl implements PlayerIdentifierService {

    private static int counter = 0;

    private static boolean gameIsReadyToPlay = false;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Override
    public Player registerPlayer() throws Exception {
        counter++;
        if (counter > 2) {
            throw new Exception("It's already two players in the game, cannot add new player");
        } else {
            gameIsReadyToPlay = counter == 2;
            Player player = counter % 2 == 1 ? Player.USER : Player.OPPONENT;
            actualPlayerService.initializePlayer(player);
            return player;
        }
    }

    @Override
    public void unregisterPlayer() {
        counter--;
    }

    @Override
    public boolean isGameAvailable() {
        if (gameIsReadyToPlay) {
            return counter == 2;
        }
        return true;
    }

}