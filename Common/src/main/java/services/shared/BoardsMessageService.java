package services.shared;

import models.Player;
import models.BoardsMessage;

/**
 * This service is responsible for retrieving message object which contains all information necessary for player
 * Method can be invoking sequentially in new thread.
 */
public interface BoardsMessageService {

    /**
     * @param player determine player. Based on this param method knows from where it should retrieves data
     * @return big message which contains whole information for player like: player board, rival board, number of rival alive ships, game state, info about game availability
     */
    BoardsMessage retrieveDataForUser(Player player);

}
