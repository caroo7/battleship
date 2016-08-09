package gui.panels.boards;

import models.GameState;
import gui.services.Publisher;
import gui.services.Subscriber;

import javax.swing.*;
import java.awt.*;


abstract class BoardGui extends JPanel implements Subscriber {

    final PlayingArea playingArea = new PlayingArea();

    abstract BoardGui addTitles();

    BoardGui addListeners() {
        return this;
    }

    BoardGui setBelowPanel(GameState gameState) {
        return this;
    }

    BoardGui() {
        setLayout(new BorderLayout());
        add(playingArea, BorderLayout.CENTER);
    }

    BoardGui addSubscription(Publisher publisher) {
        publisher.subscribe(this);
        return this;
    }
}
