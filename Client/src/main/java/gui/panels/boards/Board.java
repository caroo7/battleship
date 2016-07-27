package gui.panels.boards;
import gui.services.GameState;
import gui.services.Publisher;
import gui.services.Subscriber;

import javax.swing.*;
import java.awt.*;


abstract class Board extends JPanel implements Subscriber {

    PlayingArea playingArea=new PlayingArea();

    abstract Board addTitles();
    Board addListeners(){return this;}
    Board setBelowPanel(GameState gameState){return this;}
    Board(){
         setLayout(new BorderLayout());
         add(playingArea,BorderLayout.CENTER);
    }

     Board addSubscription(Publisher publisher){
        publisher.subscribe(this);
        return this;
    }
}
