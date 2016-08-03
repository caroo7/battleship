package models;

import static models.CellState.*;

import java.util.HashMap;
import java.util.Map;

public class Cell {

    private CellState state;
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
        Cell cell = new Cell();
        cell.state = EMPTY;
        return cell;
    }

    public static Cell createCellWithShip(Ship ship) {
        Cell cellWithShip = new Cell();
        cellWithShip.myShip = ship;
        cellWithShip.state = SHIP;
        return cellWithShip;
    }

    public CellState shoot() {
        this.state = rulesOfChangingState.get(state);
        if (state == SHOOTEDSHIP) {
            myShip.reduceShipParts();
            if (!myShip.isAlive()) {
                state = SUNK;
            }
        }
        return state;
    }

    void setState(CellState state) {
        this.state = state;
    }

    CellState getState() {
        return state;
    }
}