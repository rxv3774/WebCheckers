package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link Board} component
 */
@Tag("Model-tier")
class BoardTest {
    private Row board[];

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        board = new Row[8];
    }

    /**
     * Tests that the constructor functions correctly
     */
    @Test
    void test_constructor() {
        final Board board1 = new Board();
        assertEquals(board1.getRowsSize(), board.length);
    }

    /*
     * Tests that the Iterator hasNext functions correctly
     */
    @Test
    void test_hasNext(){
        final Board board1 = new Board();
        Iterator itr = board1.iterator();
        assertTrue(itr.hasNext());
    }

    /*
     * Tests that the Iterator next functions correctly
     */
    @Test
    void test_next() {
        final Board board1 = new Board();
        Iterator itr = board1.iterator();
        assertEquals(board, itr.next());
    }

}