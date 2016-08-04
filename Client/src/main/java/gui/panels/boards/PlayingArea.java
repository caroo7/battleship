package gui.panels.boards;

import gameLogic.CellState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class PlayingArea extends JPanel {
    final static int weight = 320;
    final static int height = 320;
    final static int size = 8;

    Map<Point, CellState> boardState = new HashMap<>();

    PlayingArea(){
        setEmptyBoard();
    }

     void setEmptyBoard() {
        setSize(new Dimension(weight, height));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardState.put(new Point(i, j), CellState.EMPTY);
            }
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(weight, height);

        Graphics2D g2 = (Graphics2D) img.getGraphics();

        g2.setColor(Color.cyan);
        g2.fillRect(0, 0, weight, height);


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g2.setColor(boardState.get(new Point(i, j)).getColor());
                g2.fillRect((weight / size) * i, (height / size) * j, (weight / size) - 2, (height / size) - 2);
            }
        }
        g.drawImage(img, 0, 0, this);
    }
}
