package services.undisclosed;

import models.GameState;
import models.Player;

public class ActualPlayerServiceImpl implements ActualPlayerService {

    private Player actualPlayer;


    @Override
    public void setActualPlayerAsFirstPlayerAtStart() {
        actualPlayer = Player.FIRST;
    }

    @Override
    public Player getActualPlayer() {
        return actualPlayer;
    }

    @Override
    public void changeActualPlayer() {
        actualPlayer = actualPlayer == Player.FIRST ? Player.SECOND : Player.FIRST;
    }
}
