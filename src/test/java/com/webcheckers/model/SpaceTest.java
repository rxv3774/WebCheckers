package com.webcheckers.model;

import javafx.scene.input.PickResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("MODEL-tier")
class SpaceTest {


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

        //Test1
        assertNotNull( new Space(cellIndex, rowIndex) );

        Piece testPiece = new Piece( Piece.Type.SINGLE, Piece.Color.RED );

        //Test2
        assertNotNull( new Space(cellIndex, rowIndex, testPiece) );
    }

    @Test
    public void getPieceNotNull() {

        when(space.getPiece()).thenReturn(piece);

        assertNotNull(space.getPiece());
    }

    @Test
    public void initializeWorks(){
        Space testSpace0 = new Space( 1,0);
        Space testSpace1 = new Space( 0,0);

        Space testSpace2W = new Space( 0,5);
        Space testSpace3R = new Space( 1,2);

        testSpace0.initialize();
        testSpace1.initialize();
        testSpace2W.initialize();
        testSpace3R.initialize();

        //Test1
        assertNotNull( testSpace0.getPiece() );

        //Test2
        assertNull( testSpace1.getPiece() );

        //Test3
        assertEquals( Piece.Color.WHITE, testSpace2W.getPieceColor() );

        //Test4
        assertEquals( Piece.Type.SINGLE, testSpace2W.getPiece().getType() );

        //Test5
        assertEquals( Piece.Color.RED, testSpace3R.getPieceColor() );

        //Test6
        assertEquals( Piece.Type.SINGLE, testSpace3R.getPiece().getType() );
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

        Space tmpSpace0 = new Space(0,0);
        Space tmpSpace1 = new Space(0,1);
        Space tmpSpace2 = new Space(3,2);
        Space tmpSpace3 = new Space(11,11);

        Piece piece0 = new Piece( Piece.Type.SINGLE, Piece.Color.RED );

        Space tmpSpace4 = new Space( 1,0, piece0);
        Space tmpSpace5 = new Space( 0,5, piece0);


        //Test1
        assertNotNull( tmpSpace0.isValid() );

        //Test2
        assertFalse( tmpSpace0.isValid() );

        //Test3
        assertTrue( tmpSpace1.isValid() );

        //Test4
        assertTrue( tmpSpace2.isValid() );

        //Test5
        assertFalse( tmpSpace3.isValid() );

        //Test6 checking for piece not null rowIndex % 2 == 0
        assertFalse( tmpSpace4.isValid() );

        //Test7 checking for piece not null rowIndex % 2 == 1
        assertFalse( tmpSpace5.isValid() );
    }

    @Test
    public void isValidTrueNotNull() {
        Space testSpace = new Space(1,3);

        assertNotNull( testSpace.isValid() );
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

        Piece pieceNull = null;
        Space tmpSpace1 = new Space(10,10, pieceNull);


        //Test1
        assertNotNull( tmp.getPiece() );

        //Test2
        assertEquals(true, tmp.pieceColorMatch( Piece.Color.RED) );

        //Test3
        assertEquals(false, tmp.pieceColorMatch( Piece.Color.WHITE) );

        assertFalse( tmpSpace1.pieceColorMatch( Piece.Color.RED ) );
    }


    @Test
    public void deepCopyWorks(){
        Piece piece = new Piece( Piece.Type.SINGLE, Piece.Color.RED);
        Space tmp = new Space(1,1, piece);

        //Test1
        assertNotNull( tmp.getPiece() );

        //Test2
        assertNotNull( tmp.deepCopy() );
        Space deepCopy = tmp.deepCopy();

        //Test3
        assertEquals( tmp.getPiece().getColor() , deepCopy.getPiece().getColor() );

        //Test4
        assertEquals( tmp.getPiece().getType() , deepCopy.getPiece().getType() );

        //Test5
        assertEquals( tmp.getRowIndex() , deepCopy.getRowIndex() );

        //Test6
        assertEquals( tmp.getCellIndex() , deepCopy.getCellIndex() );

        //Test7
        assertEquals( tmp.getPieceColor() , deepCopy.getPieceColor() );
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