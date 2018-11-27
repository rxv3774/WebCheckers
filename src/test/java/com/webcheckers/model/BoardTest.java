package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

    /**
     * Tests that the Iterator hasNext functions correctly
     */
    @Test
    void test_hasNext() {
        final Board board1 = new Board();
        Iterator itr = board1.iterator();
        assertTrue(itr.hasNext());
    }

    /**
     * Tests that the Iterator next functions correctly
     */
    @Test
    void test_next() {
        final Board board1 = new Board();
        Iterator itr = board1.iterator();
        assertNotNull(itr.next());
        board1.initialize(Piece.Color.RED);
        assertTrue(itr.next() instanceof Row);
    }

    /*
     * Tests that the Reverse Iterator hasNext functions correctly
     */
    @Test
    public void test_hasNextReverse() {
        final Board board1 = new Board();
        Iterator itr = board1.reverseIterator();
        assertTrue(itr.hasNext());
    }

    /*
     * Tests that the Reverse Iterator next functions correctly
     */
    @Test
    void test_nextReverse() {
        final Board board1 = new Board();
        Iterator itr = board1.reverseIterator();
        assertNotNull(itr.next());
        board1.initialize(Piece.Color.RED);
        assertTrue(itr.next() instanceof Row);
    }

    @Test
    public void hasPossibleMoves(){

        Board board = new Board();
        board.initialize( Piece.Color.RED);
        board.initialize( Piece.Color.WHITE);

        //This hot mess fills up the board so it's super easy to test if someone has a possible move
        for( int row = 2; row < 6; row++){
            for( int col = 0; col < 8; col++){

                if( row != 3 && row != 4){
                    Space s1;
                    Space s2;
                    if( row == 2){
                        if( col % 2 == 1) {
                            s1 = board.getSpace(new Position(row, col));
                            s2 = board.getSpace(new Position(row + 1, col - 1));
                            s1.movePieceTo(s2);
                            board.getSpace( new Position( row, col ) ).initialize();
                        }
                    }
                    else{
                        if( col % 2 == 0) {
                            s1 = board.getSpace(new Position(row, col));
                            s2 = board.getSpace(new Position(row - 1, col + 1));
                            s1.movePieceTo(s2);
                            board.getSpace( new Position( row, col ) ).initialize();
                        }
                    }

                }
            }
        }

        //Test1 this checks if red player has any possible moves
        assertFalse( board.hasPossibleMoves( Piece.Color.RED) );

        //Test2 this checks if white player has any possible moves
        assertFalse( board.hasPossibleMoves( Piece.Color.WHITE) );
    }

    @Test
    public void getSpaceWorks(){
        Board board = new Board();

        //Test1 this checks to see if it returns null for a non existent row
        assertNull( board.getSpace( new Position( -1, -1) ) );
    }

    @Test
    public void spaceHasEnemyPlayerWorks(){

        Board board = new Board();
        board.initialize( Piece.Color.RED);
        board.initialize( Piece.Color.WHITE);

        //Test1 this should return false since the next piece is red
        assertFalse( board.spaceHasEnemyPiece( new Position(0,1), true ) );

        //Test2 has no one around it so it's false
        assertFalse( board.spaceHasEnemyPiece( new Position(2,0), true ) );


        //Test3 this should return false since the next piece is white
        assertFalse( board.spaceHasEnemyPiece( new Position(7,0), false ) );

        //Test4 has no one around it so it's false
        assertFalse( board.spaceHasEnemyPiece( new Position(5,0), false ) );
    }


    @Test
    public void removePieceWorks(){

        Board board = new Board();
        board.initialize( Piece.Color.RED );

        //Test1 this proves a piece is there
        assertTrue( board.getSpace( new Position(0,1) ).hasPiece() );

        board.removePieceAtPosition( new Position( 0,1) );

        //Test2 this proves a piece is no longer there
        assertFalse( board.getSpace( new Position(0,1) ).hasPiece() );


    }


}