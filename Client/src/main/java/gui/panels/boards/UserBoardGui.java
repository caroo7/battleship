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

public class UserBoardGui extends BoardGui {
    @Autowired
    private UserBelowPanel userBelowPanel;

    private boolean isPlaying = false;

    /**
     *  update GUI using message retrieved from server side. Method is invoking periodically so in all time we have an actual GUI.
     *  If win condition happens players are unregister and game ends.
     * @param boardsMessage - message objects retrieved from server side which contains whole information necessary for GUI update.
     */
    @Override
    public void update(BoardsMessage boardsMessage) {
        setProperBoardState(boardsMessage.getUserGameState(), boardsMessage.getActualUserBoardStates());
        setShootsButtonsActivity(boardsMessage.getUserGameState());
        setBelowPanel(boardsMessage.getUserGameState());
        repaint();
        revalidate();
    }


    private void setShootsButtonsActivity(GameState gameState) {
        Buttons.FourShoots.setEnabled(gameState.getButtonsActivity());
        Buttons.ThreeShoots.setEnabled(gameState.getButtonsActivity());
        Buttons.TwoShoots.setEnabled(gameState.getButtonsActivity());
    }

    /**
     * update board before game start, when we're generating ships. After every generation board will be refreshed and will show actual ships.
     * @param ships from generator service which have to be display after every 'generate' click
     */
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
    BoardGui addTitles() {
        super.add(new JLabel("Your board"), BorderLayout.NORTH);
        return this;
    }

    @Override
    BoardGui setBelowPanel(GameState gameState) {
        super.add(userBelowPanel.getUserBelowPanel(), BorderLayout.SOUTH);
        if (isPlaying) userBelowPanel.showBelowPanel(GameState.YouArePlaying);
        else userBelowPanel.showBelowPanel(gameState);
        return this;
    }

    public void changeStateToPlaying() {
        isPlaying = true;
    }

}
