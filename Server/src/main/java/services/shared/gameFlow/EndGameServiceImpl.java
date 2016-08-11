package services.shared.gameFlow;

import services.shared.EndGameService;

/**
 * Service responsible for immediately application server side close
 */
public class EndGameServiceImpl implements EndGameService {

    /**
     * Close server side immediately
     */
    @Override
    public void endGame() {
        System.out.println("End game action happens");
        System.exit(0);
    }

}