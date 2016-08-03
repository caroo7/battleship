package services;

import models.Player;
import models.UserBoardMessage;

public interface UserBoardService {

    UserBoardMessage retrieveDataForUser(Player player);

}
