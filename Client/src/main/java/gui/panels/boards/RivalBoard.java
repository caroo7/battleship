package gui.panels.boards;

import models.BoardsMessage;
import models.CellState;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.ShootService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;


public class RivalBoard extends Board {

    @Autowired
    private ShootService shootService;

    private int shipsLeft = 12;

    private Map<Point, CellState> rivalBoardState;

    @Override
    public void update(BoardsMessage boardsMessage) {
        //TODO
    }

    @Override
    Board addTitles() {
        super.add(new JLabel("Rival board. Ships left " + shipsLeft), BorderLayout.NORTH);
        return this;
    }

    @Override
    Board addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    Point p = new Point(mouseEvent.getX() / (PlayingArea.weight / PlayingArea.size), (mouseEvent.getY() - 15) / (PlayingArea.height / PlayingArea.size));
                    System.out.println("Kliknieto mnie " + p);
                    rivalBoardState = shootService.shootOn(p);
                }

            }
        });
        return this;
    }
}
