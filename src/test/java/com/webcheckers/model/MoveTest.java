package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class MoveTest {


    private Space startSpace;
    private Space endSpace;



    @BeforeEach
    void setup() {
        startSpace = mock( Space.class );
        endSpace = mock( Space.class );
    }


    @Test
    public void constructorWorks(){
        Move tmpMove = new Move( startSpace, endSpace );

        Board tmpBoard = new Board();
        Move tmpMove1 = new Move( startSpace, endSpace, tmpBoard );

        //Test1 test for null the first constructor
        assertNotNull( tmpMove );

        //Test2 test for null the second constructor
        assertNotNull( tmpMove1 );
    }

    @Test
    public void toStringWorks(){
        Space start = new Space(0,0);
        Space end = new Space(1,1);

        Move move = new Move( start, end );

        final String compareString = "Start: (" + start.getRowIdx() + "," + start.getCellIdx() + ") End: (" + end.getRowIdx() + "," + end.getCellIdx() + ")";

        //Test1 not null toString()
        assertNotNull( move.toString() );

        //Test2 the toString returns a string in the right format
        assertEquals( compareString, move.toString() );
    }

    @Test
    public void equalsWorks(){

        Space start = new Space(0,0);
        Space end = new Space(1,1);


        Board board = new Board();

        Space end0 = new Space(3,1);

        Move move0 = new Move( start, end );
        Move move1 = new Move( start, end );
        Move move2 = new Move( start, end0 );


        //Test1 not null
        assertNotNull( move0.equals( move1 ) );

        //Test2 see if they are equal
        assertTrue( move0.equals( move1 ) );

        //Test3 not null
        assertNotNull( move0.equals( move2 ) );

        //Test4 see that they are not equal
        assertFalse( move0.equals( move2 ) );


        //Test5 this tests the instanceof check
        assertFalse( move0.equals( board ) );


        //Test6 check for different start Row
        Move movediffRowStart = new Move( new Space(0,1), new Space(0,0) );
        Move movediffRowend = new Move( new Space(0,2), new Space(0,0) );

        assertFalse( movediffRowStart.equals( movediffRowend ) );

        //Test7 check for different end Row
        Move movediffEndRow = new Move( new Space(0,0), new Space(0,1) );
        Move movediffEndRow1 = new Move( new Space(0,0), new Space(0,0) );
        assertFalse( movediffEndRow.equals( movediffEndRow1 ) );

        //Test8 check for different start col
        Move movediffStartCol = new Move( new Space(0,0), new Space(0,0) );
        Move movediffStartCol1 = new Move( new Space(1,0), new Space(0,0) );
        assertFalse( movediffStartCol.equals( movediffStartCol1 ) );

        //Test9 check for different end col
        Move movediffEndCol = new Move( new Space(0,0), new Space(0,0) );
        Move movediffEndCol1 = new Move( new Space(0,0), new Space(1,0) );
        assertFalse( movediffEndCol.equals( movediffEndCol1 ) );





    }

}