package com.webcheckers.model;

import com.webcheckers.model.Piece.Type;
import com.webcheckers.model.Piece.Color;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Tag("Model-tier")
public class PieceTest {

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

    @Test
    public void directionWorks(){
        Piece p1 = new Piece( Type.SINGLE, Color.RED );
        Piece p2 = new Piece( Type.SINGLE, Color.WHITE );

        //Test1 see if the direction doesn't return a null
        assertNotNull( p1.direction() );

        //Test2 see if it returns the correct value for red
        assertEquals( 1, p1.direction() );

        //Test3 see if it returns the correct value for white
        assertEquals( -1, p2.direction() );

    }
}
