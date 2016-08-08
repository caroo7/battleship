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


public class RivalBoardGui extends BoardGui {

    @Autowired
    private ShootService shootService;

    @Autowired
    private PlayerRegistrationService registrationService;

    private long shipsLeft;

    private int CURSOR_HEIGHT = 15;

    private int CURSOR_WIDTH = 5;

    private GameState isGamePlayed = GameState.NotYourTurn;

    private JLabel titles = new JLabel("Rival board. Ships left " + shipsLeft);

    /**
     *  update GUI using message retrieved from server side. Method is invoking periodically so in all time we have an actual GUI.
     *  If win condition happens players are unregister and game ends.
     * @param boardsMessage - message objects retrieved from server side which contains whole information necessary for GUI update.
     */
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
    BoardGui addTitles() {
        add(titles, BorderLayout.NORTH);
        return this;
    }

    @Override
    BoardGui addListeners() {
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
