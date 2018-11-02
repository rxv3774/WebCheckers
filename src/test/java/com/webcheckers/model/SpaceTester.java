package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("MODEL-tier")
class SpaceTester {


    private int cellIndex, rowIndex;
    private Piece piece;
    private Space space;


    @BeforeEach
    public void setup() {

        rowIndex = 0;
        cellIndex = 1;
        piece = mock(Piece.class);

//        space = new Space(cellIndex, rowIndex);
        space = mock(Space.class);
    }

    @Test
    public void constructorNotNull() {
        assertNotNull(new Space(cellIndex, rowIndex));
    }

    @Test
    public void getPieceNotNull() {

        when(space.getPiece()).thenReturn(piece);

        assertNotNull(space.getPiece());
    }


    @Test
    public void getRowIndexWorks() {

        Space tmpSpace1 = new Space(0,1);

        //Test0
        assertNotNull( tmpSpace1.getRowIndex() );

        //Test1
        assertEquals(1, tmpSpace1.getRowIndex() );
    }

    @Test
    public void getCellIndexWorks() {

        Space tmpSpace1 = new Space(1,0);

        //Test0
        assertNotNull( tmpSpace1.getCellIndex() );

        //Test1
        assertEquals(1, tmpSpace1.getCellIndex() );
    }

    @Test
    public void getCellIdxNotNull() {
        assertNotNull(space.getCellIndex());
    }

    @Test
    public void isValidTrue() {

//        System.out.println( space.getRowIndex() );
//        System.out.println( space.getCellIndex() );

        Space tmpSpace0 = new Space(0,0);
        Space tmpSpace1 = new Space(0,1);

        //Test1
        assertNotNull( tmpSpace0.isValid() );

        //Test2
        assertFalse( tmpSpace0.isValid() );

        //Test3
        assertTrue( tmpSpace1.isValid() );
    }

    @Test
    public void isValidTrueNotNull() {
        assertNotNull(space.isValid());
    }


    @Test
    public void getPieceWorks(){
        Piece piece = new Piece( Piece.Type.SINGLE, Piece.Color.RED);
        Space tmp = new Space(1,1, piece);

        //Test1
        assertNotNull( tmp.getPiece() );

        //Test2
        assertEquals( piece, tmp.getPiece() );
    }



    @Test
    public void pieceColorMatchWorks(){
        Piece piece = new Piece( Piece.Type.SINGLE, Piece.Color.RED);
        Space tmp = new Space(1,1, piece);

        //Test1
        assertNotNull( tmp.getPiece() );

        //Test2
        assertEquals(true, tmp.pieceColorMatch( Piece.Color.RED) );

        //Test3
        assertEquals(false, tmp.pieceColorMatch( Piece.Color.WHITE) );
    }

    @Test
    public void getPieceColorWorks(){
        Piece pieceR = new Piece( Piece.Type.SINGLE, Piece.Color.RED);
        Piece pieceW = new Piece( Piece.Type.SINGLE, Piece.Color.WHITE);
        Piece pieceNull = null;

        Space spaceR = new Space(1,1, pieceR );
        Space spaceW = new Space(1,1, pieceW );
        Space spaceNull = new Space(1,1, pieceNull );

        //Test1
        assertNotNull( spaceR.getPiece() );

        //Test2
        assertEquals( Piece.Color.RED, spaceR.getPieceColor() );

        //Test3
        assertNotEquals( Piece.Color.WHITE, spaceR.getPieceColor() );

        //Test4
        assertEquals( Piece.Color.WHITE, spaceW.getPieceColor() );

        //Test5
        assertNotEquals( Piece.Color.RED, spaceW.getPieceColor() );

        //Test6
        assertNull( spaceNull.getPieceColor() );
    }


    @Test
    public void removePieceWorks(){
        Piece pieceR = new Piece( Piece.Type.SINGLE, Piece.Color.RED);
        Space spaceR = new Space(1,1, pieceR );

        //Test1
        assertEquals( pieceR, spaceR.removePiece() );

        //Test2
        assertNull( spaceR.getPiece() );
    }

    @Test
    public void hasPieceWorks(){
        Piece pieceR = new Piece( Piece.Type.SINGLE, Piece.Color.RED);
        Space spaceR = new Space(1,1, pieceR );

        Piece pieceNull = null;
        Space spaceNull = new Space(1,1, pieceNull );

        //Test1
        assertEquals( true, spaceR.hasPiece() );

        //Test2
        assertEquals( false, spaceNull.hasPiece() );
    }


    @Test
    public void setPieceWorks(){
        Piece pieceR = new Piece( Piece.Type.SINGLE, Piece.Color.RED);

        Piece pieceNull = null;
        Space spaceNull = new Space(1,1, pieceNull );

        //Test1
        assertNull( spaceNull.getPiece() );

        spaceNull.setPiece( pieceR );

        //Test2
        assertNotNull( spaceNull.getPiece() );

        //Test3
        assertEquals( pieceR, spaceNull.getPiece() );
    }

}