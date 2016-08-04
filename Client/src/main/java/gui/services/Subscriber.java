package gui.services;

import gameLogic.Ship;
import models.BoardsMessage;

import java.util.Set;

public interface Subscriber {
    void update(BoardsMessage dataObject);

    default void update(Set<Ship> ships) {
    }
}
