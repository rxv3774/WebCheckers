package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("MODEL-tier")
class SpaceTest {


    private int cellIndex, rowIndex;
    private Piece piece;
    private Space space;


    @BeforeEach
    public void setup() {

        cellIndex = 1;
        rowIndex = 0;
        piece = mock( Piece.class );
        space = new Space(cellIndex, rowIndex);
    }

    @Test
    public void constructorNotNull(){
        assertNotNull( new Space(cellIndex, rowIndex ) );
    }

    @Test
    public void getPieceNotNull(){
        assertNotNull( space.getPiece() );
    }

    @Test
    public void getCellIdx(){
        assertEquals( 1, space.getCellIdx() );
    }

    @Test
    public void getCellIdxNotNull(){
        assertNotNull( space.getCellIdx() );
    }

    @Test
    public void isValidTrue(){
        assertTrue( space.isValid() );
    }

    @Test
    public void isValidTrueNotNull(){
        assertNotNull( space.isValid() );
    }

}