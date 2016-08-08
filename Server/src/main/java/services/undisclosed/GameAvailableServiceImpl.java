package services.undisclosed;

import org.springframework.beans.factory.annotation.Autowired;
import services.shared.PlayerRegistrationService;

/**
 * Responsible for checking if game is still available and if communication between clients and server should still exist
 */
public class GameAvailableServiceImpl {

    @Autowired
    private PlayerRegistrationService playerRegistrationService;

    private boolean areTwoPlayersConnected = false;

    /**
     * Checks if already two players made connection and if one of them close application
     * @return true - if we have already two registered player and if they are still playing, false - if one of the player disconnects during the game
     */
    public boolean isGameAvailable() {
        if (checkIfTwoPlayersAreConnected()) {
            areTwoPlayersConnected = true;
        }

        return !areTwoPlayersConnected || checkIfTwoPlayersAreConnected();
    }

    private boolean checkIfTwoPlayersAreConnected() {
        return playerRegistrationService.getConnectedPlayersNumber() == PlayerRegistrationService.MAX_NUMBERS_OF_PLAYERS;
    }
}