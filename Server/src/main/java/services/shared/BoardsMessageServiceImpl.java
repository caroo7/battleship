package services.shared;

import gameLogic.CellState;
import models.BoardsMessage;
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
    GameAvailableService gameAvailableService;

    @Autowired
    ActualPlayerService actualPlayerService;

    @Autowired
    BoardStateService boardStateService;

    @Autowired
    AliveShipsService aliveShipsService;

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