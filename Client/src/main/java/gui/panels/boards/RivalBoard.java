package gui.panels.boards;

import gui.services.DataObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class RivalBoard extends Board {

    private int shipsLeft=12;

    @Override
    public void update(DataObject dataObject) {
        //TODO
    }

    @Override
    Board addTitles() {
        super.add(new JLabel("Rival board. Ships left "+shipsLeft),new BorderLayout().NORTH);
        return this;
    }

    @Override
    Board addListeners() {
          addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent mouseEvent) {
                  if(SwingUtilities.isLeftMouseButton(mouseEvent)){
                      Point p = new Point(mouseEvent.getX() / (PlayingArea.weight / PlayingArea.size), (mouseEvent.getY()-15) / (PlayingArea.height / PlayingArea.size));
                      System.out.println("Kliknieto mnie "+ p);
                  }

              }
          });
        return this;
    }
}
