package gui.services;

import gameLogic.Ship;
import models.BoardsMessage;

import java.util.Set;

@FunctionalInterface
public interface Subscriber {

    /**
     * update GUI using message retrieved from server side
     * @param boardsMessage - message objects retrieved from server side which contains whole information necessary for GUI update.
     */
    void update(BoardsMessage boardsMessage);

    /**
     * update board before game start, when we're generating ships.
     * @param ships from generator service which have to be display after every 'generate' click
     */
    default void updateGeneratedShips(Set<Ship> ships) {
    }
}