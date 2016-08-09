package workers;

import gui.services.Publisher;
import models.BoardsMessage;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Time interval between two requests to server
     */
    private static final long THREAD_INTERVAL = 100L;

    /**
     * Thread which works separately next to Swing EDT. Thanks that it doesn't cause GUI freezing.
     * Responsibility of this thread is to periodically send requests and retrieve data packages with information necessary for GUI update.
     * @param player determine for which player message is addressed.
     */
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
                    } catch (Exception e) {
                        System.out.println("Server doesn't work");
                        System.exit(0);
                    }
                    userBoardPublisher.notifyAllSubscribers(actualMessage);
                }
            }
        };

        backgroundThread.execute();
    }

    private void closeIfGameWasEnded(BoardsMessage actualMessage) {
        if (!actualMessage.isGameAvailable()) {
            endGameService.endGame();
        }
    }


}