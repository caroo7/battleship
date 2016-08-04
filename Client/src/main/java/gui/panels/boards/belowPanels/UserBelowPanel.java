package gui.panels.boards.belowPanels;

import models.GameState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

public class UserBelowPanel extends JPanel {

    @Autowired
    private BelowPanelsFactory belowPanelsFactory;

    private CardLayout belowCardLayout;

    @PostConstruct
    public void init() {
        belowCardLayout = new CardLayout();
        initPanel();
    }

    private void initPanel() {
        setLayout(belowCardLayout);
        add(belowPanelsFactory.getBelowPanel(GameState.Playing), GameState.Playing.toString());
        add(belowPanelsFactory.getBelowPanel(GameState.YourTurn), GameState.YourTurn.toString());
        add(belowPanelsFactory.getBelowPanel(GameState.NotYourTurn), GameState.NotYourTurn.toString());
    }


    public void showBelowPanel(GameState gameState) {
        belowCardLayout.show(this, gameState.toString());
    }

}
