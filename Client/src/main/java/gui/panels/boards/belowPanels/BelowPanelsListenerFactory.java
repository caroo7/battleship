package gui.panels.boards.belowPanels;

import models.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.GameStateService;

import java.awt.event.ActionListener;

public class BelowPanelsListenerFactory {

    @Autowired
    private GameStateService gameStateService;

    @Autowired
    private UserBelowPanel userBelowPanel;

    public ActionListener getOkListner() {
        return e -> {
            gameStateService.startPlaying();
            userBelowPanel.showBelowPanel(GameState.Playing);
        };
    }

}
