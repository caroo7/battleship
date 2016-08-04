package gui.panels.boards.belowPanels;

import gui.panels.boards.UserBoard;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.ActionListener;

public class BelowPanelsListenerFactory {

    @Autowired
    private UserBoard userBoardPanel;

    public ActionListener getOkListener() {
        return e -> {
                  userBoardPanel.changeStateToPlaying();
        };
    }

}
