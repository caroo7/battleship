package workers;

import models.Player;
import models.UserBoardMessage;
import org.springframework.beans.factory.annotation.Autowired;
import services.UserBoardService;

import javax.swing.*;
import java.util.List;

public class UserBackgroundThread {

    @Autowired
    private UserBoardService userBoardService;

    private static final long THREAD_INTERVAL = 100l;

    // Player should be identified already by UserPanelController (using PlayerRegistrationService)
    // and also game should be initialized using the same controller (GameInitializerService) - using player and set of ships
    public void execute(Player player) {

        SwingWorker<Void, UserBoardMessage> backgroundThread = new SwingWorker<Void, UserBoardMessage>() {

            @Override
            protected Void doInBackground() throws Exception {
                while(true) {
                    publish(userBoardService.retrieveDataForUser(player));
                    try {
                        Thread.sleep(THREAD_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // display some error in GUI using controller?
                    }
                }
            }

            @Override
            protected void process(List<UserBoardMessage> chunks) {
                // notify GUI using UserPanelController (actual element is: chunks.get(chunks.size() - 1))
            }
        };

        backgroundThread.execute();
    }


}