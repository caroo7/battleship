package gameLogic;

import java.util.HashMap;
import java.util.Map;

import static gameLogic.CellState.*;

public class Cell {

    protected CellState state;
    private Map<CellState, CellState> rulesOfChangingState;

    {
        rulesOfChangingState = new HashMap<>();
        rulesOfChangingState.put(EMPTY, SHOOTEDEMPTY);
        rulesOfChangingState.put(SHIP, SHOOTEDSHIP);
    }

    private Ship myShip;

    private Cell() {
    }

    public static Cell createEmptyCell() {
        return new Cell();
    }

    public static Cell createCellWithShip(Ship ship) {
        Cell cellWithShip = new Cell();
        cellWithShip.myShip = ship;
        cellWithShip.state = SHIP;
        return cellWithShip;
    }

    public CellState shoot() {
        this.state = rulesOfChangingState.get(state);
        if (state == SHOOTEDSHIP && !myShip.isAlive()) {
            state = SUNK;
        }
        return state;
    }
}
