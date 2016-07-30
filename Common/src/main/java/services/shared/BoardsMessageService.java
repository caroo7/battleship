package services.shared;

import models.Player;
import models.BoardsMessage;

public interface BoardsMessageService {

    BoardsMessage retrieveDataForUser(Player player) throws Exception;

}
