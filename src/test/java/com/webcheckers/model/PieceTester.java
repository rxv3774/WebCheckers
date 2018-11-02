package com.webcheckers.model;

import com.webcheckers.model.Piece.Type;
import com.webcheckers.model.Piece.Color;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Tag("Model-tier")
public class PieceTester {

    // Test piece
    private final Piece piece = new Piece(Type.SINGLE, Color.RED);

    // Test piece values
    private Type actualType = Type.SINGLE;
    private Color actualColor = Color.RED;

    /**
     * Tests to ensure that the constructor builds the piece properly.
     */
    @Test
    public void test_constructor() {
        assertNotNull(piece.getColor());
        assertNotNull(piece.getType());
    }

    /**
     * Tests to ensure that getColor works properly.
     */
    @Test
    public void test_getColor() {
        assertEquals(actualColor, piece.getColor());
    }

    /**
     * Tests to ensure that getType works properly.
     */
    @Test
    public void test_getType() {
        assertEquals(actualType, piece.getType());
    }
}
