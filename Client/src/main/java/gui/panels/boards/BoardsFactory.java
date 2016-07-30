package gui.panels.boards;

import gui.services.BoardPanelType;
import gui.services.GameState;
import gui.services.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.BoardsMessageService;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardsFactory {

    @Autowired
    private BoardsMessageService boardsMessageService;

    @Autowired
    private UserBoard userBoard;

    @Autowired
    private RivalBoard rivalBoard;

    @Autowired
    private Publisher userBoardPublisher;

    @Autowired
    private Publisher rivalBoardPublisher;

    private Map<BoardPanelType, JPanel> boards = new HashMap<>();

    @PostConstruct
    public void boardFactoryInitialize() {
        initBoardsMap();
    }

    public JPanel getBoardPanel(BoardPanelType boardPanelType) {
        return boards.getOrDefault(boardPanelType, new JPanel());
    }

    private void initBoardsMap() {
        boards.put(BoardPanelType.Playing, createPlayingBoard());
    }

    private JPanel createPlayingBoard() {
        JPanel playingBoard = new JPanel(new GridLayout(1, 2));
        playingBoard.add(userBoard.addSubscription(userBoardPublisher).addTitles().setBelowPanel(GameState.YourTurn));
        playingBoard.add(rivalBoard.addSubscription(rivalBoardPublisher).addTitles().addListeners());
        return playingBoard;
    }
}
