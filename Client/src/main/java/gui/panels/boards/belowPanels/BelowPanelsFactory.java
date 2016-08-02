package gui.panels.boards.belowPanels;

import models.GameState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class BelowPanelsFactory {

    @Autowired
    private BelowPanelsListenerFactory belowPanelsListenerFactory;

    private Map<GameState, JPanel> belowPanels = new HashMap<>();

    @PostConstruct
    public void init() {
        initBelowPanelsMap();
    }

    JPanel getBelowPanel(GameState gameState) {
        return belowPanels.getOrDefault(gameState, new JPanel());
    }

    private void initBelowPanelsMap() {
        belowPanels.put(GameState.YourTurn, yourTurnBelowPanel());
        belowPanels.put(GameState.NotYourTurn, notYourTurnBelowPanel());
    }

    private JPanel yourTurnBelowPanel() {
        JPanel belowPanel = new JPanel();
        JLabel yourTurnPanel = new JLabel("Your turn click ok ");
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(belowPanelsListenerFactory.getOkListner());

        belowPanel.add(yourTurnPanel);
        belowPanel.add(okButton);

        return belowPanel;
    }

    private JPanel notYourTurnBelowPanel() {
        JPanel belowPanel = new JPanel();
        belowPanel.add(new JLabel("Not your turn"));
        return belowPanel;
    }
}
