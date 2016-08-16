package gui.services;

import models.BoardsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Realize observer pattern. Contains list of subscribers and gives a possibility to add new one and notify all existing subscribers
 */
public class Publisher {

    private final List<Subscriber> subscribersList = new ArrayList<>();

    /**
     * Add new subscriber to the subscriber list
     * @param subscriber - add it to subscribers list
     */
    public void subscribe(Subscriber subscriber) {
        subscribersList.add(subscriber);
    }

    /**
     * Inform all subscribers by give them actual data necessary for GUI update
     * @param boardsMessage - message retrieved from server side which contains data necessary for GUI update
     */
    public void notifyAllSubscribers(BoardsMessage boardsMessage) {
        subscribersList.stream().forEach(subscriber -> subscriber.update(boardsMessage));
    }

}