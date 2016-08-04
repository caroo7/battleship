package gui.panels.boards;

import gameLogic.CellState;
import gameLogic.Ship;
import gui.panels.boards.belowPanels.UserBelowPanel;
import gui.panels.buttons.Buttons;
import models.BoardsMessage;
import models.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class UserBoard extends Board {
    @Autowired
    private UserBelowPanel userBelowPanel;

    boolean isPlaying = false;

    @Override
    public void update(BoardsMessage boardsMessage) {
        setProperBoardState(boardsMessage.getUserGameState(), boardsMessage.getActualUserBoardStates());
        setShootsButtonsActivity(boardsMessage.getUserGameState());
        userBelowPanel.showBelowPanel(boardsMessage.getUserGameState());
        repaint();
    }


    private void setShootsButtonsActivity(GameState isYourTurn) {
        Buttons.FourShoots.setEnabled(isYourTurn.getButtonsActivity());
        Buttons.ThreeShoots.setEnabled(isYourTurn.getButtonsActivity());
        Buttons.TwoShoots.setEnabled(isYourTurn.getButtonsActivity());
    }

    public void updateGeneratedShips(Set<Ship> ships) {
        playingArea.setEmptyBoard();
        ships.stream().forEach(ship -> ship.getCoordinates()
                .stream().forEach(point -> playingArea.boardState.put(point, CellState.SHIP)));
        repaint();
    }

    private void setProperBoardState(GameState gameState, Map<Point, CellState> boardState) {
        if (gameState == GameState.NotYourTurn) {
            isPlaying = false;
        }
        if (isPlaying) {
            playingArea.boardState = boardState;
        } else {
            playingArea.setEmptyBoard();
        }
    }

    @Override
    Board addTitles() {
        super.add(new JLabel("Your board"), BorderLayout.NORTH);
        return this;
    }

    @Override
    Board setBelowPanel(GameState gameState) {
        super.add(userBelowPanel.getUserBelowPanel(), BorderLayout.SOUTH);
        userBelowPanel.showBelowPanel(gameState);
        return this;
    }

    public void changeStateToPlaying() {
        isPlaying = true;
    }

}
