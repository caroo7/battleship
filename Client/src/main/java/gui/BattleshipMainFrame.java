package gui;

import gui.panels.boards.BoardsFactory;
import gui.panels.buttons.ButtonsPanelFactory;
import gui.services.BoardPanelType;
import org.springframework.beans.factory.annotation.Autowired;
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

    // TODO this shouldn't be here!!!
    @Autowired
    private PlayerRegistrationService registrationService;

    private JFrame battleshipMainFrame = new JFrame();

    @PostConstruct
    public void show() {
        setMainFrameProperties();
        addBoardsPanel();
        addButtonsPanel();
        setVisibleAfterFrameCreation();
    }

    private void setMainFrameProperties() {
        battleshipMainFrame.setSize(new Dimension(720, 450));
        battleshipMainFrame.setTitle("Battleship");
        battleshipMainFrame.setLayout(new BorderLayout());
        battleshipMainFrame.setResizable(false);
        battleshipMainFrame.setLocationRelativeTo(null);
        battleshipMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addRunAwayPlayerListener();
    }

    private void addBoardsPanel() {
        battleshipMainFrame.add(boardsFactory.getBoardPanel(BoardPanelType.Playing), BorderLayout.CENTER);
    }

    private void addButtonsPanel() {
        battleshipMainFrame.add(buttonsPanelFactory.getButtonsPanel(), BorderLayout.SOUTH);
    }

    private void setVisibleAfterFrameCreation() {
        battleshipMainFrame.setVisible(true);
    }


    // TODO this shouldn't be here!!!
    private void addRunAwayPlayerListener() {
        battleshipMainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                registrationService.unregisterPlayer();
                event.getWindow().dispose();
            }
        });
    }
}
