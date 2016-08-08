package gui.panels.boards.belowPanels;

import models.GameState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;


public class UserBelowPanel {
    @Autowired
    private BelowPanelsFactory belowPanelsFactory;

    private JPanel userBelowPanel = new JPanel();

    private CardLayout belowCardLayout = new CardLayout();

    /**
     * Return below client panel depends on actual fame state value. There are 3 possibilities:
     * - YouArePlaying - don't see nothing on below panel, is your turn and you can shoot
     * - YouCanPlay - see "Your turn" label and button which allows to see your actual board
     * - NotYourTurn - see label "Not Your Turn" and have to wait for turn
     * Use card layout to achieve that
     * @return appropriate panel
     */
    public JPanel getUserBelowPanel() {
        userBelowPanel.setLayout(belowCardLayout);
        userBelowPanel.add(belowPanelsFactory.getBelowPanel(GameState.YouArePlaying), GameState.YouArePlaying.toString());
        userBelowPanel.add(belowPanelsFactory.getBelowPanel(GameState.YouCanPlay), GameState.YouCanPlay.toString());
        userBelowPanel.add(belowPanelsFactory.getBelowPanel(GameState.NotYourTurn), GameState.NotYourTurn.toString());
        return userBelowPanel;
    }

    public void showBelowPanel(GameState gameState) {
        belowCardLayout.show(userBelowPanel, gameState.toString());
    }
}
