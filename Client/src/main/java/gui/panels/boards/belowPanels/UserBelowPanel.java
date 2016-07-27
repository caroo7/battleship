package gui.panels.boards.belowPanels;

import gui.services.GameState;

import javax.swing.*;
import java.awt.*;

public class UserBelowPanel extends JPanel {
    private CardLayout belowCardLayout = new CardLayout();
    private BelowPanelsFactory belowPanelsFactory = new BelowPanelsFactory();

    public UserBelowPanel(){
        initPanel();
    }
   private void initPanel() {
         setLayout(belowCardLayout);
         add(belowPanelsFactory.getBelowPanel(GameState.Playing),GameState.Playing.toString());
         add(belowPanelsFactory.getBelowPanel(GameState.YourTurn),GameState.YourTurn.toString());
         add(belowPanelsFactory.getBelowPanel(GameState.NotYourTurn),GameState.NotYourTurn.toString());
    }


    public void showBelowPanel(GameState gameState){
        belowCardLayout.show(this,gameState.toString());
    }

}
