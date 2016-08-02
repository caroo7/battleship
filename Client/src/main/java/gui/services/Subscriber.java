package gui.services;

import models.BoardsMessage;
import models.Ship;

import java.util.Set;

public interface Subscriber {
    void update(BoardsMessage dataObject);

    default void update(Set<Ship> ships) {
    }
}
