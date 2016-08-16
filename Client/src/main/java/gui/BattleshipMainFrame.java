package gui;

import configuration.Config;
import gui.panels.boards.BoardsFactory;
import gui.panels.buttons.ButtonsPanelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.MainFramePositionService;
import services.shared.PlayerRegistrationService;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BattleshipMainFrame {

    @Autowired
    private BoardsFactory boardsFactory;

    @Autowired
    private ButtonsPanelFactory buttonsPanelFactory;

    @Autowired
    private PlayerRegistrationService registrationService;

    @Autowired
    private MainFramePositionService mainFramePositionService;

    private final JFrame battleshipMainFrame = new JFrame();

    private final WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            registrationService.unregisterPlayer();
            System.exit(0);
        }
    };

    /**
     * After bean creation this method will be invoked, create initial GUI components and make them visible
     */
    @PostConstruct
    public void show() {
        setMainFrameProperties();
        addBoardsPanel();
        addButtonsPanel();
        setVisibleAfterFrameCreation();
    }

    private void setMainFrameProperties() {
        battleshipMainFrame.setSize(new Dimension(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT));
        battleshipMainFrame.setTitle("Battleship");
        battleshipMainFrame.setLayout(new BorderLayout());
        battleshipMainFrame.setResizable(false);
        battleshipMainFrame.setLocation(mainFramePositionService.getProperFrameLocation());
        battleshipMainFrame.addWindowListener(windowAdapter);
        battleshipMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addBoardsPanel() {
        battleshipMainFrame.add(boardsFactory.getBoardPanel(), BorderLayout.CENTER);
    }

    private void addButtonsPanel() {
        battleshipMainFrame.add(buttonsPanelFactory.getButtonsPanel(), BorderLayout.SOUTH);
    }

    private void setVisibleAfterFrameCreation() {
        battleshipMainFrame.setVisible(true);
    }
}
