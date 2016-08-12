package gameLogic;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipTest {

    private final Set<Point> points = new HashSet<>();
    private Ship ship;

    @BeforeMethod
    private void prepareShip() {
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        ship = new Ship(points, null);
    }

    @Test
    public void testAmountOfAlivePoints() {

        //given BeforeMethod

        //when
        boolean containsPoint_0_0 = ship.containsPoint(new Point(0, 0));
        boolean containsPoint_0_1 = ship.containsPoint(new Point(0, 1));
        boolean containsPoint_6_4 = ship.containsPoint(new Point(6, 4));

        //then
        assertTrue(containsPoint_0_0);
        assertTrue(containsPoint_0_1);
        assertFalse(containsPoint_6_4);
    }

    @Test
    public void testWhenIsAlive() {

        //given BeforeMethod

        //when
        boolean isAliveAtStart = ship.isAlive();
        ship.reduceShipParts();
        boolean isAliveAfterReducingOnePart = ship.isAlive();
        ship.reduceShipParts();
        boolean isNotAlive = ship.isAlive();

        //then
        assertTrue(isAliveAtStart);
        assertTrue(isAliveAfterReducingOnePart);
        assertFalse(isNotAlive);
    }
}
