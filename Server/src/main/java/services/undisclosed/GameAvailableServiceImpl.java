package services.undisclosed;

import org.springframework.beans.factory.annotation.Autowired;
import services.shared.PlayerRegistrationService;
import services.undisclosed.GameAvailableService;

public class GameAvailableServiceImpl implements GameAvailableService {

    @Autowired
    private PlayerRegistrationService playerRegistrationService;

    private boolean areTwoPlayersConnected = false;

    @Override
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