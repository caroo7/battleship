package workers;

import gui.services.Publisher;
import models.BoardsMessage;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteConnectFailureException;
import services.shared.BoardsMessageService;
import services.shared.EndGameService;

import javax.swing.*;
import java.util.List;

public class UserBackgroundThread {

    @Autowired
    private BoardsMessageService boardsMessageService;

    @Autowired
    private Publisher userBoardPublisher;

    @Autowired
    private Publisher rivalBoardPublisher;

    @Autowired
    private EndGameService endGameService;

    private static final long THREAD_INTERVAL = 100L;

    public void execute(Player player) {
        SwingWorker<Void, BoardsMessage> backgroundThread = new SwingWorker<Void, BoardsMessage>() {

            @Override
            protected Void doInBackground() throws Exception {
                do {
                    BoardsMessage msg = boardsMessageService.retrieveDataForUser(player);
                    publish(msg);
                    Thread.sleep(THREAD_INTERVAL);
                    if (msg != null && msg.getRivalShipsLeft() == 0) return null;
                } while (true);
            }

            @Override
            protected void process(List<BoardsMessage> chunks) {
                BoardsMessage actualMessage = chunks.get(chunks.size() - 1);
                if (actualMessage != null) {

                    try {
                        closeIfGameWasEnded(actualMessage);
                    } catch (RemoteConnectFailureException e) {
                        System.out.println("Server doesn't work");
                        System.exit(0);
                    }

                    userBoardPublisher.notifyAllSubscribers(actualMessage);
                }
            }
        };

        backgroundThread.execute();
    }

    private void closeIfGameWasEnded(BoardsMessage actualMessage) throws RemoteConnectFailureException {
        if (!actualMessage.isGameAvailable()) {
            endGameService.endGame();
        }
    }


}