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

    /**
     * Initialize panels which will be displayed using card layout later (based on game state value)
     */
    @PostConstruct
    public void init() {
        initBelowPanelsMap();
    }

    JPanel getBelowPanel(GameState gameState) {
        return belowPanels.getOrDefault(gameState, new JPanel());
    }

    private void initBelowPanelsMap() {
        belowPanels.put(GameState.YouCanPlay, youCanPlayPanel());
        belowPanels.put(GameState.YouArePlaying, youArePlayingPanel());
        belowPanels.put(GameState.NotYourTurn, notYourTurnBelowPanel());
    }

    private JPanel youCanPlayPanel() {
        JPanel belowPanel = new JPanel();
        JLabel youCanPlayPanel = new JLabel("To see your board click ");
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(belowPanelsListenerFactory.getOkListener());

        belowPanel.add(youCanPlayPanel);
        belowPanel.add(okButton);

        return belowPanel;
    }

    private JPanel notYourTurnBelowPanel() {
        JPanel belowPanel = new JPanel();
        belowPanel.add(new JLabel("Not your turn"));
        return belowPanel;
    }

    private JPanel youArePlayingPanel() {
        return new JPanel();
    }

}