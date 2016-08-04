package gameLogic;

import configuration.Config;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static gameLogic.CellState.*;
import static gameLogic.CellState.AROUNDSUNK;
import static gameLogic.CellState.SHOOTEDEMPTY;

public class ShipsUtility {

    public static Map<Point, CellState> getRealBoardStateAfterShootingVar2() {
        Map<Point, CellState> result = createEmptyBoard();
        result.put(new Point(0, 0), SHIP);
        result.put(new Point(0, 1), SHIP);
        result.put(new Point(0, 4), SHIP);
        result.put(new Point(1, 4), SHIP);
        result.put(new Point(2, 4), SHIP);
        result.put(new Point(3, 4), SHIP);
        result.put(new Point(6, 0), SHIP);
        result.put(new Point(6, 2), SUNK);
        result.put(new Point(5, 1), AROUNDSUNK);
        result.put(new Point(5, 2), AROUNDSUNK);
        result.put(new Point(5, 3), AROUNDSUNK);
        result.put(new Point(6, 1), AROUNDSUNK);
        result.put(new Point(6, 3), AROUNDSUNK);
        result.put(new Point(7, 1), AROUNDSUNK);
        result.put(new Point(7, 2), AROUNDSUNK);
        result.put(new Point(7, 3), AROUNDSUNK);
        return result;
    }

    public static Ship createShipWithoutNeighbours(Point ... point) {
        Set<Point> coordinates = new HashSet<>();
        for (int i = 0; i < point.length; i++) {
            coordinates.add(point[i]);
        }
        return new Ship(coordinates, null);
    }

   public static Ship createShipWithNeighbours(Set<Point> ship, Set<Point> neighbours) {
       return new Ship(ship,neighbours);
    }

    public static Set<Ship> getSetOf4Ships(){
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

    public static Map<Point, CellState> getRealBoardStateBeforeShooting(){
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

    public static Map<Point, CellState> getRealBoardStateAfterShootingVar1() {
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
        result.put(new Point(1, 2), AROUNDSUNK);
        result.put(new Point(1, 2), AROUNDSUNK);
        result.put(new Point(0, 2), AROUNDSUNK);
        result.put(new Point(3, 3), SHOOTEDEMPTY);
        return result;
    }

    public static Map<Point, CellState> getRepresentationOfBoardStateAfterShootingVar1() {
        Map<Point, CellState> result = createEmptyBoard();
        result.put(new Point(0, 0), SUNK);
        result.put(new Point(0, 1), SUNK);
        result.put(new Point(0, 4), SHOOTEDSHIP);
        result.put(new Point(1, 0), AROUNDSUNK);
        result.put(new Point(1, 1), AROUNDSUNK);
        result.put(new Point(1, 2), AROUNDSUNK);
        result.put(new Point(1, 2), AROUNDSUNK);
        result.put(new Point(0, 2), AROUNDSUNK);
        result.put(new Point(3, 3), SHOOTEDEMPTY);
        return result;
    }

    public static Map<Point, CellState> getRepresentationOfBoardStateAfterShootingVar2() {
        Map<Point, CellState> result = createEmptyBoard();
        result.put(new Point(6, 2), SUNK);
        result.put(new Point(5, 1), AROUNDSUNK);
        result.put(new Point(5, 2), AROUNDSUNK);
        result.put(new Point(5, 3), AROUNDSUNK);
        result.put(new Point(6, 1), AROUNDSUNK);
        result.put(new Point(6, 3), AROUNDSUNK);
        result.put(new Point(7, 1), AROUNDSUNK);
        result.put(new Point(7, 2), AROUNDSUNK);
        result.put(new Point(7, 3), AROUNDSUNK);
        return result;
    }

    public static Map<Point, CellState> createEmptyBoard() {
        Map<Point, CellState> result = new HashMap<>();
        for (int i = 0; i < Config.BOARD_SIZE; i++) {
            for (int j = 0; j < Config.BOARD_SIZE; j++) {
                result.put(new Point(i, j), EMPTY);
            }
        }
        return result;
    }
}
