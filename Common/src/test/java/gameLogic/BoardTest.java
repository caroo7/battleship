package gameLogic;

import configuration.Config;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static gameLogic.CellState.*;

public class BoardTest {

    @Test
    public void TestBoardBeforeShooting() {

        //given
        Board board = new Board();
        Set<Ship> ships = prepareSetOfShips();
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ships);
        board.init(shipManager);

        //when
        Map<Point, CellState> boardStateBEFOREShooting = board.representCurrentBoardState();

        //then
        assertEquals(boardStateBEFOREShooting, exeptedStateBeforeShooting());
    }

    @Test
    public void TestBoardAfterShooting() {

        //given
        Board board = new Board();
        Set<Ship> ships = prepareSetOfShips();
        ShipManager shipManager = new ShipManager();
        shipManager.initShips(ships);
        board.init(shipManager);

        //when
        board.representBoardStateAfterClickingOn(new Point(0, 0));
       board.representBoardStateAfterClickingOn(new Point(0, 1));
       board.representBoardStateAfterClickingOn(new Point(0, 4));
        Map<Point, CellState> boardStateAFTERShooting = board.representBoardStateAfterClickingOn(new Point(3, 3));

        //then
        assertEquals(boardStateAFTERShooting, exeptedStateAfterShooting());
    }

    private Set<Ship> prepareSetOfShips() {
        Set<Ship> ships = new HashSet<>();

        Set<Point> firstShipPoints = new HashSet<>();
        firstShipPoints.add(new Point(0, 0));
        firstShipPoints.add(new Point(0, 1));
        Set<Point> firstShipNeighbours = new HashSet<>();
        firstShipNeighbours.add(new Point(1, 0));
        firstShipNeighbours.add(new Point(1, 1));
        firstShipNeighbours.add(new Point(1, 2));
        firstShipNeighbours.add(new Point(0, 2));
        ships.add(ShipsUtility.createShipWithNeighbours(firstShipPoints, firstShipNeighbours));

        Set<Point> secondShipPoints = new HashSet<>();
        secondShipPoints.add(new Point(0, 4));
        secondShipPoints.add(new Point(1, 4));
        secondShipPoints.add(new Point(2, 4));
        secondShipPoints.add(new Point(3, 4));
        Set<Point> secondShipNeighbours = new HashSet<>();
        secondShipNeighbours.add(new Point(0, 3));
        secondShipNeighbours.add(new Point(0, 5));
        secondShipNeighbours.add(new Point(1, 3));
        secondShipNeighbours.add(new Point(1, 5));
        secondShipNeighbours.add(new Point(2, 3));
        secondShipNeighbours.add(new Point(2, 5));
        secondShipNeighbours.add(new Point(3, 3));
        secondShipNeighbours.add(new Point(3, 5));
        secondShipNeighbours.add(new Point(4, 3));
        secondShipNeighbours.add(new Point(4, 4));
        secondShipNeighbours.add(new Point(4, 5));
        ships.add(ShipsUtility.createShipWithNeighbours(secondShipPoints, secondShipNeighbours));

        Set<Point> thirdShipPoints = new HashSet<>();
        thirdShipPoints.add(new Point(6,0));
        Set<Point> thirdShipNeighbours = new HashSet<>();
        thirdShipNeighbours.add(new Point(5,0));
        thirdShipNeighbours.add(new Point(7,0));
        thirdShipNeighbours.add(new Point(5,1));
        thirdShipNeighbours.add(new Point(6,1));
        thirdShipNeighbours.add(new Point(7,1));
        ships.add(ShipsUtility.createShipWithNeighbours(thirdShipPoints,thirdShipNeighbours));

        Set<Point> fourthShipPoints = new HashSet<>();
        fourthShipPoints.add(new Point(6, 2));
        Set<Point> fourthShipNeighbours = new HashSet<>();
        fourthShipNeighbours.add(new Point(5,1));
        fourthShipNeighbours.add(new Point(5,2));
        fourthShipNeighbours.add(new Point(5,3));
        fourthShipNeighbours.add(new Point(6,1));
        fourthShipNeighbours.add(new Point(6,3));
        fourthShipNeighbours.add(new Point(7,1));
        fourthShipNeighbours.add(new Point(7,2));
        fourthShipNeighbours.add(new Point(7,3));
        ships.add(ShipsUtility.createShipWithNeighbours(fourthShipPoints,fourthShipNeighbours));

        return ships;
    }

    private Map<Point, CellState> exeptedStateAfterShooting() {
        Map<Point, CellState> result = createEmptyBoard();
        result.put(new Point(0, 0), SUNK);
        result.put(new Point(0, 1), SUNK);
        result.put(new Point(0, 4), SHOOTEDSHIP);
        result.put(new Point(1, 4), SHIP);
        result.put(new Point(2, 4), SHIP);
        result.put(new Point(3, 4), SHIP);
        result.put(new Point(6, 0), SHIP);
        result.put(new Point(6, 2), SHIP);
        result.put(new Point(1, 0), AROUNDSUNK);
        result.put(new Point(1, 1), AROUNDSUNK);
        result.put(new Point(1,2), AROUNDSUNK);
        result.put(new Point(1,2), AROUNDSUNK);
        result.put(new Point(0, 2), AROUNDSUNK);
        result.put(new Point(3, 3), SHOOTEDEMPTY);
        return result;
    }

    private Map<Point, CellState> exeptedStateBeforeShooting() {
        Map<Point, CellState> result = createEmptyBoard();
        result.put(new Point(0, 0), SHIP);
        result.put(new Point(0, 1), SHIP);
        result.put(new Point(0, 4), SHIP);
        result.put(new Point(1, 4), SHIP);
        result.put(new Point(2, 4), SHIP);
        result.put(new Point(3, 4), SHIP);
        result.put(new Point(6, 0), SHIP);
        result.put(new Point(6, 2), SHIP);
        return result;

    }

    private Map<Point, CellState> createEmptyBoard() {
        Map<Point, CellState> result = new HashMap<>();
        for (int i = 0; i < Config.BOARD_SIZE; i++) {
            for (int j = 0; j < Config.BOARD_SIZE; j++) {
                result.put(new Point(i, j), EMPTY);
            }
        }
        return result;
    }
}
