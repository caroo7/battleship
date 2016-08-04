package gui.services;

import models.BoardsMessage;
import models.Ship;

import java.util.Set;

public interface Subscriber {
    void updateGeneratedShips(BoardsMessage dataObject);

    default void updateGeneratedShips(Set<Ship> ships) {
    }
}
