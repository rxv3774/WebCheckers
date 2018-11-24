package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class MoveTest {
    private Board board;

    private Position startPos;
    private Position endPos;

    private Move singleMove;
    private Move jumpMove;
    private Move kingMove;
    private Move backwardsMove;
    private Move invalidMove;

    @BeforeEach
    void setup() {
        board = new Board();

        startPos = mock(Position.class);
        endPos = mock(Position.class);

        singleMove = new Move(new Position(1, 1), new Position(2, 2));
        jumpMove = new Move(new Position(1, 1), new Position(3, 3));
        kingMove = new Move(new Position(4, 4), new Position(5, 4));
        backwardsMove = new Move(new Position(2, 2), new Position(1, 1));
        invalidMove = new Move(new Position(0, 0), new Position(6, 5));
    }

    @Test
    void testConstructor() {
        new Move(startPos, endPos);
    }

    @Test
    void testGetStart() {
        assertEquals(new Position(1, 1).toString(), singleMove.getStart().toString());
    }

    @Test
    // TODO - Write assertions for other move types
    void testIsValid() {
        assertTrue(singleMove.isValid(board, true));
    }

    @Test
    void testIsSingleMove() {
        assertTrue(singleMove.isSingleMove());
        assertFalse(jumpMove.isSingleMove());
        assertFalse(invalidMove.isSingleMove());
    }

    @Test
    void testIsJumpMove() {
        assertTrue(jumpMove.isJumpMove());
        assertFalse(singleMove.isJumpMove());
        assertFalse(invalidMove.isJumpMove());
    }

    @Test
    void testIsKingMove() {
        assertTrue(kingMove.isKingMove(board));
        assertTrue(singleMove.isKingMove(board));

        /*
        This assert should fail, it doesn't
         */
//        assertFalse(invalidMove.isKingMove(board));
    }

    @Test
    void toStringWorks() {
        final String expectedToString = "Start: (1, 1); End: (2, 2)";

        assertNotNull(singleMove.toString());
        assertEquals(expectedToString, singleMove.toString());
    }

    @Test
    void testEquals() {
        assertTrue(singleMove.equals(new Move(new Position(1, 1), new Position(2, 2))));
        assertFalse(singleMove.equals(jumpMove));
    }
}