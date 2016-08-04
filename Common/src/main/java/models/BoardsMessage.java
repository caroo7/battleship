package models;

import java.awt.*;
import gameLogic.CellState;
import java.io.Serializable;
import java.util.Map;

public final class BoardsMessage implements Serializable {

    private boolean isGameAvailable;
    private GameState userGameState;
    private GameState gameState;
    private Map<Point, CellState> actualUserBoardStates;
    private Map<Point, CellState> actualRivalBoardState;
    private long rivalShipsLeft;

    public BoardsMessage(boolean isGameAvailable, GameState userGameState, Map<Point, CellState> actualUserBoardStates, Map<Point, CellState> actualRivalBoardState, long rivalShipsLeft) {
        this.isGameAvailable = isGameAvailable;
        this.userGameState = userGameState;
        this.actualUserBoardStates = actualUserBoardStates;
        this.actualRivalBoardState = actualRivalBoardState;
        this.rivalShipsLeft = rivalShipsLeft;
    }

    public boolean isGameAvailable() {
        return isGameAvailable;
    }


    public GameState getUserGameState() {
        return userGameState;
    }

    public Map<Point, CellState> getActualUserBoardStates() {
        return actualUserBoardStates;
    }

    public Map<Point, CellState> getActualRivalBoardState() {
        return actualRivalBoardState;
    }

    public long getRivalShipsLeft() {
        return rivalShipsLeft;
    }
}