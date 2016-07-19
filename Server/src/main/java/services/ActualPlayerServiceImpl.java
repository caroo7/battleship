package services;

import models.Player;

public class ActualPlayerServiceImpl implements ActualPlayerService {

    private Player actualPlayer;

    @Override
    public void initializePlayer(Player player) {
        this.actualPlayer = player;
    }

    @Override
    public boolean isActualPlayer(Player player) {
        return player == this.actualPlayer;
    }

    @Override
    public void changeActualPlayer() {
        actualPlayer = (actualPlayer == Player.USER) ? Player.OPPONENT : Player.USER;
    }

}
