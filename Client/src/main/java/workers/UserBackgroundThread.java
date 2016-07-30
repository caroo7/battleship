package workers;

import gui.services.Publisher;
import models.Player;
import models.BoardsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.BoardsMessageService;

import javax.swing.*;
import java.util.List;

public class UserBackgroundThread {

    @Autowired
    private BoardsMessageService boardsMessageService;

    @Autowired
    private Publisher userBoardPublisher;

    @Autowired
    Publisher rivalBoardPublisher;

    private static final long THREAD_INTERVAL = 5000L;

    public void execute(Player player) {
        SwingWorker<Void, BoardsMessage> backgroundThread = new SwingWorker<Void, BoardsMessage>() {

            @Override
            protected Void doInBackground() throws Exception {
                while(true) {
                    try {
                        publish(boardsMessageService.retrieveDataForUser(player));
                        Thread.sleep(THREAD_INTERVAL);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        // display error in GUI
                    }
                }
            }

            @Override
            protected void process(List<BoardsMessage> chunks) {
                BoardsMessage actualMessage = chunks.get(chunks.size() - 1);

                System.out.println("is game available: " + actualMessage.isGameAvailable());
                System.out.println("is Your Turn: " + actualMessage.isYourTurn());
                System.out.println("user actual board state: " + actualMessage.getActualUserBoardStates());
                System.out.println("rival actual board state: " + actualMessage.getActualRivalBoardState());
                System.out.println("rival ships left number: " + actualMessage.getRivalShipsLeft());
                System.out.println("******************************************************************");

                userBoardPublisher.notifyAllSubscribers(actualMessage);
            }
        };

        backgroundThread.execute();
    }


}