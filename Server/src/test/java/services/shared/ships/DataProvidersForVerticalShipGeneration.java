package services.shared.ships;

import configuration.Config;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public class DataProvidersForVerticalShipGeneration {
    private static int boardSize = Config.BOARD_SIZE;


    @DataProvider
    static Object[][] validCoordinatesForFourLengthVerticalShip() {
        return getValidVerticalCoordinates(4);
    }

    @DataProvider
    static Object[][] validCoordinatesForThreeLengthVerticalShip() {
        return getValidVerticalCoordinates(3);
    }

    @DataProvider
    static Object[][] validCoordinatesForTwoLengthVerticalShip() {
        return getValidVerticalCoordinates(2);
    }

    @DataProvider
    static Object[][] validCoordinatesForOneLengthShip() {
        return getValidVerticalCoordinates(1);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForFourLengthVerticalShip() {
        return getInvalidVerticalCoordinates(4);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForThreeLengthVerticalShip() {
        return getInvalidVerticalCoordinates(3);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForTwoLengthVerticalShip() {
        return getInvalidVerticalCoordinates(2);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForOneLengthShip() {
        return getInvalidVerticalCoordinates(1);
    }

    private static Object[][] getValidVerticalCoordinates(int shipLength) {
        /*
        Substract 1 from shipLength, because validCoordinates arrays indexes start from 0
        * */
        int firstInvalidRowInBoard = boardSize - (shipLength - 1);
        int numberOfValidCoordinates = boardSize * firstInvalidRowInBoard;

        Object[][] validCoordinates = new Object[numberOfValidCoordinates][2];

        for (int i = 0; i < numberOfValidCoordinates; i++) {
            validCoordinates[i][0] = i / firstInvalidRowInBoard;
            validCoordinates[i][1] = i % firstInvalidRowInBoard;
        }
        return validCoordinates;
    }

    public static Object[][] getInvalidVerticalCoordinates(int shipLength) {
        return Stream.concat(Arrays.stream(invalidCoordinatesInsideTheBoard(shipLength)), Arrays.stream(invalidCoordinatesOutsideTheBoard()))
                .toArray(Object[][]::new);
    }


    private static Object[][] invalidCoordinatesInsideTheBoard(int shipLength) {
        int firstInvalidRowInBoard = boardSize - (shipLength - 1);
        int numberOfInvalidCoordinatesInsideTheBoard = boardSize * (boardSize - firstInvalidRowInBoard);
        Object[][] invalidCoordinates = new Object[numberOfInvalidCoordinatesInsideTheBoard][2];
        for (int i = 0; i < numberOfInvalidCoordinatesInsideTheBoard; i++) {
            invalidCoordinates[i][0] = (i % firstInvalidRowInBoard) + firstInvalidRowInBoard;
            invalidCoordinates[i][1] = firstInvalidRowInBoard + i / firstInvalidRowInBoard;

        }
        return invalidCoordinates;
    }

    private static Object[][] invalidCoordinatesOutsideTheBoard() {
        Object[][] invalidCoordinates = new Object[5][2];

        invalidCoordinates[0][0] = Integer.MIN_VALUE;
        invalidCoordinates[0][1] = Integer.MIN_VALUE;

        invalidCoordinates[1][0] = Integer.MAX_VALUE;
        invalidCoordinates[1][1] = Integer.MAX_VALUE;

        invalidCoordinates[2][0] = -1;
        invalidCoordinates[2][1] = 3;

        invalidCoordinates[3][0] = Config.BOARD_SIZE + 1;
        invalidCoordinates[3][1] = 3;

        invalidCoordinates[4][0] = Config.BOARD_SIZE + 2;
        invalidCoordinates[4][1] = -1;

        return invalidCoordinates;
    }
}
