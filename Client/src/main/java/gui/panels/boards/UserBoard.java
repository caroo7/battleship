package gui.panels.boards;

import gui.panels.boards.belowPanels.UserBelowPanel;
import gui.panels.buttons.Buttons;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class UserBoard extends Board {
    @Autowired
    private UserBelowPanel userBelowPanel;

    private final Player user = Player.FIRST;

    @Override
    public void update(BoardsMessage boardsMessage) {
        playingArea.boardState = boardsMessage.getActualUserBoardStates();
        setShootsButtonsActivity(boardsMessage.getGameState());
        userBelowPanel.showBelowPanel(boardsMessage.getGameState());
        repaint();
    }

    private void setShootsButtonsActivity(GameState isYourTurn) {
        Buttons.FourShoots.setEnabled(isYourTurn.getButtonsActivity());
        Buttons.ThreeShoots.setEnabled(isYourTurn.getButtonsActivity());
        Buttons.TwoShoots.setEnabled(isYourTurn.getButtonsActivity());
    }

    public void update(Set<Ship> ships) {
        playingArea.setEmptyBoard();
        ships.stream().forEach(ship -> ship.getCoordinates()
                .stream().forEach(point -> playingArea.boardState.put(point, CellState.SHIP)));
        repaint();
    }

    @Override
    Board addTitles() {
        super.add(new JLabel("Your board"), BorderLayout.NORTH);
        return this;
    }

    @Override
    Board setBelowPanel(GameState gameState) {
        userBelowPanel.showBelowPanel(gameState);
        super.add(userBelowPanel, BorderLayout.SOUTH);
        return this;
    }
}
