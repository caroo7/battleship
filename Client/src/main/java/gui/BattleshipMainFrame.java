package gui;

import gui.panels.boards.BoardsFactory;
import gui.panels.buttons.ButtonsPanelFactory;
import gui.services.BoardPanelType;

import javax.swing.*;
import java.awt.*;

public class BattleshipMainFrame {
    private JFrame battleshipMainFrame = new JFrame();


    public void show(){
        setMainFrameProperties();
        addBoardsPanel();
        addButtonsPanel();
        setVisibileAfterFrameCreation();
    }

    private void setMainFrameProperties(){
        battleshipMainFrame.setSize(new Dimension(720,400));
        battleshipMainFrame.setTitle("Battleship");
        battleshipMainFrame.setLayout(new BorderLayout());
        battleshipMainFrame.setResizable(false);
        battleshipMainFrame.setLocationRelativeTo(null);
        battleshipMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addBoardsPanel(){
         battleshipMainFrame.add(new BoardsFactory().getBoardPanel(BoardPanelType.Playing),BorderLayout.CENTER);
    }

    private void addButtonsPanel(){
          battleshipMainFrame.add(new ButtonsPanelFactory().getButtonsPanel(), BorderLayout.SOUTH);
    }

   private void setVisibileAfterFrameCreation(){
       battleshipMainFrame.setVisible(true);
   }
}
