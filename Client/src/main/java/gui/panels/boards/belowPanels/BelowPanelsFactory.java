package gui.panels.boards.belowPanels;

import gui.services.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BelowPanelsFactory {
    private Map<GameState,JPanel> belowPanels = new HashMap<>();

    public BelowPanelsFactory(){
        initBelowPanelsMap();
    }
    public JPanel getBelowPanel(GameState gameState){
        return belowPanels.getOrDefault(gameState,new JPanel());
    }

    private void initBelowPanelsMap ()  {
        belowPanels.put(GameState.YourTurn,yourTurnBelowPanel());
        belowPanels.put(GameState.NotYourTurn,notYourTurnBelowPanel());
    }

    private JPanel yourTurnBelowPanel(){
        JPanel belowPanel = new JPanel();
        JLabel yourTurnPanel =  new JLabel("Your turn click ok ");
        JButton okButton = new JButton("Ok");


        belowPanel.add(yourTurnPanel);
        belowPanel.add(okButton);
        return belowPanel;
    }

    private JPanel notYourTurnBelowPanel(){
        JPanel belowPanel = new JPanel();
        belowPanel.add(new JLabel("Not your turn"));
        return belowPanel;
    }
}
