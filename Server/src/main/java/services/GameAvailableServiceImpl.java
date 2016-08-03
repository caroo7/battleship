package services;

import org.springframework.beans.factory.annotation.Autowired;

public class GameAvailableServiceImpl implements GameAvailableService{

    @Autowired
    private PlayerRegistrationService playerRegistrationService;

    private boolean areTwoPlayersConnected = false;

    @Override
    public boolean isGameAvailable() {
        if (checkIfTwoPlayersAreConnected()) {
            areTwoPlayersConnected = true;
        }

        return areTwoPlayersConnected ? checkIfTwoPlayersAreConnected() : true;
    }

    private boolean checkIfTwoPlayersAreConnected() {
        return playerRegistrationService.getConnectedPlayersNumber() == playerRegistrationService.MAX_NUMBERS_OF_PLAYERS;
    }
}