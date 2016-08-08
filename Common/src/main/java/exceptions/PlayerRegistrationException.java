package exceptions;

import services.shared.PlayerRegistrationService;

public class PlayerRegistrationException extends Exception {

    public PlayerRegistrationException(String msg) {
        super(msg);
    }

}
