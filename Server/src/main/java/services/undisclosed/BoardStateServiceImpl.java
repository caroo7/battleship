package services.undisclosed;

import gameLogic.Board;
import gameLogic.CellState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.Map;

/**
 * Responsible for transform user and rival boards to proper form acceptable by GUI
 */
public class BoardStateServiceImpl {

    /**
     * Contains first player board statuses
     */
    @Autowired
    private Board firstPlayerBoard;

    /**
     * Contains second player board statuses
     */
    @Autowired
    private Board secondPlayerBoard;

    /**
     * Retrieve actual board state as Map<Point, CellState>, cause this representation is used to update board in GUI.
     * @param player - determine which board will be choose
     * @return Map where each cell on the board is key and has own status depends on game situation
     */
    public Map<Point, CellState> getUserBoardState(Player player) {
        Board actualBoard = player == Player.FIRST ? firstPlayerBoard : secondPlayerBoard;
        return actualBoard.representCurrentBoardState();
    }

    /**
     * Retrieve actual board state as Map<Point, CellState>, cause this representation is used to update board in GUI.
     * Gives a possibility to retrieve filtered board. Hide information about rival ships because we don't want to show them for player.
     * @param player - determine which board will be choose (get a rival board)
     * @return filtered Map where each cell on the board is key and has own status depends on game situation.
     */
    public Map<Point, CellState> getRivalBoardState(Player player) {
        Board actualBoard = player == Player.FIRST ? secondPlayerBoard : firstPlayerBoard;
        Map<Point, CellState> boardState = actualBoard.representCurrentBoardState();

        boardState.keySet().stream().forEach(point -> {
            if (boardState.get(point) == CellState.SHIP) {
                boardState.put(point, CellState.EMPTY);
            }
        });
        return boardState;
    }

}