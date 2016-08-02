package services.shared;

import models.BoardsMessage;
import models.CellState;
import models.GameState;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.undisclosed.ActualPlayerService;
import services.undisclosed.AliveShipsService;
import services.undisclosed.BoardStateService;
import services.undisclosed.GameAvailableService;

import java.awt.*;
import java.util.Map;

public class BoardsMessageServiceImpl implements BoardsMessageService {

    @Autowired
    private GameAvailableService gameAvailableService;

    @Autowired
    private ActualPlayerService actualPlayerService;

    @Autowired
    private GameStateService gameStateService;

    @Autowired
    private BoardStateService boardStateService;

    @Autowired
    private AliveShipsService aliveShipsService;

    @Override
    public BoardsMessage retrieveDataForUser(Player player) {

        boolean isGameAvailable = gameAvailableService.isGameAvailable();

        GameState gameState = getGameState(player);

        Map<Point, CellState> userBoardStates = boardStateService.getUserBoardState(player);

        Map<Point, CellState> rivalBoardStates = boardStateService.getRivalBoardState(player);

        Long rivalShipsLeft = aliveShipsService.getRivalAliveAmountOfShips(player);

        return rivalShipsLeft == null ? null : new BoardsMessage(isGameAvailable, gameState, userBoardStates, rivalBoardStates, rivalShipsLeft);
    }

    private GameState getGameState(Player player) {
        return actualPlayerService.getActualPlayer() != player ? GameState.NotYourTurn : (gameStateService.isPlayerPlaying() ? GameState.Playing : GameState.YourTurn);
    }
}