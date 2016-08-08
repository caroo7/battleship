package gui.panels.boards.belowPanels;

import gui.panels.boards.UserBoardGui;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.ActionListener;

public class BelowPanelsListenerFactory {
    @Autowired
    private UserBoardGui userBoardPanel;

    ActionListener getOkListener() {
        return e -> userBoardPanel.changeStateToPlaying();
    }
}
