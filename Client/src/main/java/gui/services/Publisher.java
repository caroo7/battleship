package gui.services;

import models.BoardsMessage;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Subscriber> subscribersList = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribersList.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribersList.remove(subscriber);
    }

    public void notifyAllSubscribers(BoardsMessage boardsMessage) {
        subscribersList.stream().forEach(subscriber -> subscriber.updateGeneratedShips(boardsMessage));
    }

}