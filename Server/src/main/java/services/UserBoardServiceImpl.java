package services;

import models.Player;
import models.UserBoardMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class UserBoardServiceImpl implements UserBoardService {

    @Autowired
    PlayerIdentifierService playerIdentifierService;

    @Autowired
    ActualPlayerService actualPlayerService;

    @Override
    public UserBoardMessage retrieveDataForUser(Player player) {

        boolean isGameAvailable = playerIdentifierService.isGameAvailable();

        boolean isYourTurn = actualPlayerService.isActualPlayer(player);

        // Map<Point, BoardElementState> boardStates = get data from new service

        return new UserBoardMessage(isGameAvailable, isYourTurn);
    }
}
