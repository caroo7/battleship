package gui.panels.boards;

import gui.panels.boards.belowPanels.UserBelowPanel;
import gui.services.DataObject;
import gui.services.GameState;
import javax.swing.*;
import java.awt.*;

class UserBoard extends Board {


    @Override
    public void update(DataObject dataObject) {
         //TODO

    }

     @Override
     Board addTitles() {
         super.add(new JLabel("Your board"),BorderLayout.NORTH);
         return this;
     }

     @Override
     Board setBelowPanel(GameState gameState){
            UserBelowPanel userBelowPanel = new UserBelowPanel();
            userBelowPanel.showBelowPanel(GameState.YourTurn);
            super.add(userBelowPanel,BorderLayout.SOUTH);
             return this;
     }
 }
