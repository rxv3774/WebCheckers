package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PositionTest {
    private Position startPos;
    private Position endPos;
    private Position goodPos;
    private Position badPos;

    @BeforeEach
    void setup() {
        startPos = new Position(5, 5);
        endPos = new Position(7, 7);
        goodPos = new Position(2, 5);
        badPos = new Position(19, 52);
    }

    @Test
    void testConstructor() {
        new Position(1, 1);
    }

    @Test
    void testGetMiddle() {
        assertEquals(new Position(6, 6).toString(), startPos.getMiddle(endPos).toString());
    }

    @Test
    void testOutOfBounds() {
        assertTrue(badPos.isOutOfBounds());
        assertFalse(goodPos.isOutOfBounds());
    }

    @Test
    void testGetters() {
        assertEquals(goodPos.getRow(), 2);
        assertEquals(goodPos.getCell(), 5);
    }

    @Test
    void testToString() {
        assertEquals(new Position(2, 5).toString(), goodPos.toString());
    }
}
