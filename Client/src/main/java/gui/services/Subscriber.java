package gui.services;

import models.BoardsMessage;

public interface Subscriber {

    void update(BoardsMessage dataObject);
}
