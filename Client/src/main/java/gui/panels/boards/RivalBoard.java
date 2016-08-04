package gui.panels.boards;

import models.BoardsMessage;
import models.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.PlayerRegistrationService;
import services.shared.ShootService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RivalBoard extends Board {

    @Autowired
    private ShootService shootService;

    @Autowired
    private PlayerRegistrationService registrationService;

    private long shipsLeft = 0;

    private int CURSOR_HEIGHT = 15;

    private int CURSOR_WIDTH = 5;

    private GameState isGamePlayed = GameState.NotYourTurn;

    private JLabel titles = new JLabel("Rival board. Ships left " + shipsLeft);

    @Override
    public void update(BoardsMessage boardsMessage) {
        playingArea.boardState = boardsMessage.getActualRivalBoardState();
        shipsLeft = boardsMessage.getRivalShipsLeft();
        isGamePlayed = boardsMessage.getUserGameState();
        switchTitle();
        revalidate();
        repaint();

        if (shipsLeft == 0) {
            JOptionPane.showMessageDialog(this, "You win!", "End game", JOptionPane.INFORMATION_MESSAGE);
            registrationService.unregisterBothPlayers();
            System.exit(0);
        }
    }

    private void switchTitle() {
        remove(titles);
        titles = new JLabel("Rival board. Ships left " + shipsLeft);
        add(titles, BorderLayout.NORTH);
    }

    @Override
    Board addTitles() {
        add(titles, BorderLayout.NORTH);
        return this;
    }

    @Override
    Board addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (isGamePlayed != GameState.NotYourTurn && SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    Point p = new Point((mouseEvent.getX() + CURSOR_WIDTH) / (PlayingArea.weight / PlayingArea.size), (mouseEvent.getY() - CURSOR_HEIGHT) / (PlayingArea.height / PlayingArea.size));
                    shootService.shootOn(p);
                }
            }
        });
        return this;
    }
}
