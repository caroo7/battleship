package services.shared;

import org.springframework.remoting.RemoteConnectFailureException;

public class EndGameServiceImpl implements EndGameService {

    @Override
    public void endGame() {
        System.out.println("End game action happens");
        System.exit(0);
    }

}