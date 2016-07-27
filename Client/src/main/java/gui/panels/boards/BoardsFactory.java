package gui.panels.boards;

import gui.services.BoardPanelType;
import gui.services.GameState;
import gui.services.Publisher;
import gui.services.PublisherExample;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardsFactory {
    private Map<BoardPanelType,JPanel> boards=new HashMap<>();
    private Publisher userBoardPublisher = new PublisherExample();
    private Publisher rivalBoardPublisher = new PublisherExample();


    public BoardsFactory() {initBoardsMap();}

     public JPanel getBoardPanel(BoardPanelType boardPanelType){return boards.getOrDefault(boardPanelType,new JPanel());}

       private void initBoardsMap(){boards.put(BoardPanelType.Playing,createPlayingBoard());}

       private JPanel createPlayingBoard(){
           JPanel playingBoard = new JPanel(new GridLayout(1,2));
           playingBoard.add(new UserBoard().addSubscription(userBoardPublisher).addTitles().setBelowPanel(GameState.YourTurn));
           playingBoard.add(new RivalBoard().addSubscription(rivalBoardPublisher).addTitles().addListeners());
           return playingBoard;
       }
}
