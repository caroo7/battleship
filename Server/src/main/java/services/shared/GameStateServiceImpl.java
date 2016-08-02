package services.shared;

import models.GameState;

public class GameStateServiceImpl implements GameStateService {
    private GameState gameState = GameState.YourTurn;

    @Override
    public boolean isPlayerPlaying() {
        return gameState == GameState.Playing ? true : false;
    }

    @Override
    public void startPlaying() {
        gameState = GameState.Playing;
    }
}
