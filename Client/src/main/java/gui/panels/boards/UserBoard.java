package gui.panels.boards;

import gui.panels.boards.belowPanels.UserBelowPanel;
import gui.services.GameState;
import models.BoardsMessage;
import models.Player;

import javax.swing.*;
import java.awt.*;

public class UserBoard extends Board {

    private final Player user = Player.FIRST;

    @Override
    public void update(BoardsMessage boardsMessage) {
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
