package gameLogic;

import java.awt.*;


public enum CellState {
    EMPTY(Color.blue), SHIP(Color.white), SHOOTEDEMPTY(Color.cyan), SHOOTEDSHIP(Color.orange), SUNK(Color.gray), AROUNDSUNK(Color.cyan);

    private Color color;

    CellState (Color color){
        this.color = color;
    }
}
