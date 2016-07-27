package gui.services;

import java.awt.*;

public enum  BoardElementState {
    EMPTY(Color.blue),SHIP(Color.white), SHOOTEDSHIP(Color.orange),  SHOOTEDEMPTY(Color.cyan), SINKED(Color.red) ;

    private Color color;

    BoardElementState(Color color){
        this.color=color;
    }

    public Color getColor(){
        return color;
    }
}
