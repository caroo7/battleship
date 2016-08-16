package services.shared.board;

import gameLogic.CellState;
import models.BoardsMessage;
import models.GameState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.BoardsMessageService;
import services.undisclosed.ActualPlayerServiceImpl;
import services.undisclosed.AliveShipsServiceImpl;
import services.undisclosed.BoardStateServiceImpl;
import services.undisclosed.GameAvailableServiceImpl;

import java.awt.*;
import java.util.Map;

/**
 * This service is responsible for retrieving message object which contains all information necessary for player
 * Method can be invoking sequentially in new thread.
 */
public class BoardsMessageServiceImpl implements BoardsMessageService {

    @Autowired
    GameAvailableServiceImpl gameAvailableService;

    @Autowired
    ActualPlayerServiceImpl actualPlayerService;

    @Autowired
    BoardStateServiceImpl boardStateService;

    @Autowired
    AliveShipsServiceImpl aliveShipsService;

    /**
     * @param player determine player. Based on this param method knows from where it should retrieves data
     * @return big message which contains whole information for player, like: player board, rival board, number of rival alive ships, game state, info about game availability
     */
    @Override
    public BoardsMessage retrieveDataForUser(Player player) {

        boolean isGameAvailable = gameAvailableService.isGameAvailable();

        GameState userGameState = getUserGameState(player);

        Map<Point, CellState> userBoardStates = boardStateService.getUserBoardState(player);

        Map<Point, CellState> rivalBoardStates = boardStateService.getRivalBoardState(player);

        Long rivalShipsLeft = aliveShipsService.getRivalAliveAmountOfShips(player);

        return rivalShipsLeft == null ? null : new BoardsMessage(isGameAvailable, userGameState, userBoardStates, rivalBoardStates, rivalShipsLeft);
    }

    private GameState getUserGameState(Player player) {
        return actualPlayerService.getActualPlayer() != player ? GameState.NotYourTurn : GameState.YouCanPlay;
    }
}