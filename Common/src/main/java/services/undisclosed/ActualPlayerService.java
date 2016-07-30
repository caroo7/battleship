package services.undisclosed;

import models.Player;

public interface ActualPlayerService {

    void setActualPlayerAsFirstPlayerAtStart();

    Player getActualPlayer();

    void changeActualPlayer();

}