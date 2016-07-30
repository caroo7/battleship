package services.shared;

import models.CellState;
import models.Player;
import models.BoardsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.BoardsMessageService;
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
    private BoardStateService boardStateService;

    @Autowired
    private AliveShipsService aliveShipsService;

    @Override
    public BoardsMessage retrieveDataForUser(Player player) throws Exception {

        boolean isGameAvailable = gameAvailableService.isGameAvailable();

        boolean isYourTurn = actualPlayerService.getActualPlayer() == player;

        Map<Point, CellState> userBoardStates = boardStateService.getUserBoardState(player);

        Map<Point, CellState> rivalBoardStates = boardStateService.getRivalBoardState(player);

        long rivalShipsLeft = aliveShipsService.getRivalAliveAmountOfShips(player);

        return new BoardsMessage(isGameAvailable, isYourTurn, userBoardStates, rivalBoardStates, rivalShipsLeft);
    }

}