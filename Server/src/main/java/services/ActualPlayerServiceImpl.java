package services;

import models.Player;

public class ActualPlayerServiceImpl implements ActualPlayerService {

    private Player actualPlayer;

    @Override
    public void setActualPlayer(Player player) {
        this.actualPlayer = player;
    }

    @Override
    public boolean isActualPlayer(Player player) {
        return player == this.actualPlayer;
    }


}
