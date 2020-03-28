package pbt.humbug.model;

import g54327.humbug.model.Structures.Direction;
import g54327.humbug.model.Structures.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests position.
 *
 * @author Pierre Bettens (pbt)
 */
public class PositionTest {

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNext_north() {
        System.out.println("next north");
        Position instance = new Position(1, 0);
        Position expResult = new Position(0, 0);
        Position result = instance.next(Direction.NORTH);
        assertEquals(expResult, result);
    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNext_south() {
        System.out.println("next south");
        Position instance = new Position(3, 4);
        Position expResult = new Position(4, 4);
        Position result = instance.next(Direction.SOUTH);
        assertEquals(expResult, result);
    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNext_east() {
        System.out.println("next east");
        Position instance = new Position(2, 5);
        Position expResult = new Position(2, 6);
        Position result = instance.next(Direction.EAST);
        assertEquals(expResult, result);
    }

    /**
     * Test of next method, of class Position.
     */
    @Test
    public void testNext_west() {
        System.out.println("next west");
        Position instance = new Position(5, 2);
        Position expResult = new Position(5, 1);
        Position result = instance.next(Direction.WEST);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals_sameobject() {
        System.out.println("equals some object");
        Position position1 = new Position(2, 3);
        assertTrue(position1.equals(position1));
    }

    @Test
    public void testEquals_differentreference() {
        System.out.println("equals differents references");
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);
        assertTrue(position1.equals(position2));
    }


    @Test
    public void testEquals_differentobject() {
        System.out.println("equals differents objects");
        Position position1 = new Position(2, 3);
        Position position2 = new Position(1, 4);
        assertFalse(position1.equals(position2));
    }

    @Test
    public void testHashcode_1() {
        System.out.println("hashcode");
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        assertTrue(position1.hashCode() == position1.hashCode());
        assertTrue(position1.hashCode() == position2.hashCode());
    }


}
