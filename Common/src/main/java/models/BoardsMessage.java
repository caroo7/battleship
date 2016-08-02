package models;

import gameLogic.CellState;

import java.io.Serializable;
import java.util.Map;
import java.awt.*;

public final class BoardsMessage implements Serializable {

    private boolean isGameAvailable;
    private boolean isYourTurn;
    private Map<Point, CellState> actualUserBoardStates;
    private Map<Point, CellState> actualRivalBoardState;
    private long rivalShipsLeft;

    public BoardsMessage(boolean isGameAvailable, boolean isYourTurn, Map<Point, CellState> actualUserBoardStates, Map<Point, CellState> actualRivalBoardState, long rivalShipsLeft) {
        this.isGameAvailable = isGameAvailable;
        this.isYourTurn = isYourTurn;
        this.actualUserBoardStates = actualUserBoardStates;
        this.actualRivalBoardState = actualRivalBoardState;
        this.rivalShipsLeft = rivalShipsLeft;
    }

    public boolean isGameAvailable() {
        return isGameAvailable;
    }

    public boolean isYourTurn() {
        return isYourTurn;
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