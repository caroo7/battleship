package services.shared.ships;

import configuration.Config;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public class DataProvidersForHorizontalShipGeneration {
    private static int boardSize = Config.BOARD_SIZE;

    @DataProvider
    static Object[][] validCoordinatesForFourLengthHorizontalShip() {
        return getValidHorizontalCoordinates(4);
    }

    @DataProvider
    static Object[][] validCoordinatesForThreeLengthHorizontalShip() {
        return getValidHorizontalCoordinates(3);
    }

    @DataProvider
    static Object[][] validCoordinatesForTwoLengthHorizontalShip() {
        return getValidHorizontalCoordinates(2);
    }

    @DataProvider
    static Object[][] validCoordinatesForOneLengthShip() {
        return getValidHorizontalCoordinates(1);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForFourLengthHorizontalShip() {
        return getInvalidHorizontalCoordinates(4);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForThreeLengthHorizontalShip() {
        return getInvalidHorizontalCoordinates(3);
    }

    @DataProvider
    static Object[][] invalidCoordinatesForTwoLengthHorizontalShip() {return getInvalidHorizontalCoordinates(2);}

    @DataProvider
    static Object[][] invalidCoordinatesForOneLengthShip() {
        return getInvalidHorizontalCoordinates(1);
    }


    private static Object[][] getValidHorizontalCoordinates(int shipLength) {
        /*
        Substract 1 from shipLength, because validCoordinates arrays indexes start from 0
        * */
        int firstInvalidColumnInBoard = boardSize - (shipLength - 1);
        int numberOfValidCoordinates = boardSize * firstInvalidColumnInBoard;

        Object[][] validCoordinates = new Object[numberOfValidCoordinates][2];

        for (int i = 0; i < numberOfValidCoordinates; i++) {
            validCoordinates[i][0] = (i / boardSize);
            validCoordinates[i][1] = i % boardSize;
        }

        return validCoordinates;
    }

    public static Object[][] getInvalidHorizontalCoordinates(int shipLength) {
        return Stream.concat(Arrays.stream(invalidCoordinatesInsideTheBoard(shipLength)), Arrays.stream(invalidCoordinatesOutsideTheBoard()))
                .toArray(Object[][]::new);
    }

    private static Object[][] invalidCoordinatesInsideTheBoard(int shipLength) {
        int firstInvalidColumnInBoard = boardSize - (shipLength - 1);
        int numberOfInvalidCoordinatesInsideTheBoard = boardSize * (boardSize - firstInvalidColumnInBoard);
        Object[][] invalidCoordinates = new Object[numberOfInvalidCoordinatesInsideTheBoard][2];

        for (int i = 0; i < numberOfInvalidCoordinatesInsideTheBoard; i++) {
            invalidCoordinates[i][0] = (i % boardSize);
            invalidCoordinates[i][1] = i % (boardSize - firstInvalidColumnInBoard) + firstInvalidColumnInBoard;

        }
        return invalidCoordinates;
    }


    private static Object[][] invalidCoordinatesOutsideTheBoard() {
        Object[][] invalidCoordinates = new Object[5][2];

        invalidCoordinates[0][0] = Integer.MIN_VALUE;
        invalidCoordinates[0][1] = Integer.MIN_VALUE;

        invalidCoordinates[1][0] = Integer.MAX_VALUE;
        invalidCoordinates[1][1] = Integer.MAX_VALUE;

        invalidCoordinates[2][0] = -3;
        invalidCoordinates[2][1] = 3;

        invalidCoordinates[3][0] = Config.BOARD_SIZE + 2;
        invalidCoordinates[3][1] = 3;

        invalidCoordinates[4][0] = Config.BOARD_SIZE + 2;
        invalidCoordinates[4][1] = -1;

        return invalidCoordinates;
    }
}
