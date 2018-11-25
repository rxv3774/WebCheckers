package com.webcheckers.model;

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
        assertNotNull(new Space(rowIndex, cellIndex));

        Piece testPiece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);

        //Test2
        assertNotNull(new Space(cellIndex, rowIndex, testPiece));
    }

    @Test
    public void getPieceNotNull() {

        when(space.getPiece()).thenReturn(piece);

        assertNotNull(space.getPiece());
    }

    @Test
    public void initializeWorks() {
        Space testSpace0 = new Space(0, 1);
        Space testSpace1 = new Space(0, 0);

        Space testSpace2W = new Space(5, 0);
        Space testSpace3R = new Space(2, 1);

        testSpace0.initialize();
        testSpace1.initialize();
        testSpace2W.initialize();
        testSpace3R.initialize();

        //Test1
        assertNotNull(testSpace0.getPiece());

        //Test2
        assertNull(testSpace1.getPiece());

        //Test3
        assertEquals(Piece.Color.WHITE, testSpace2W.getPieceColor());

        //Test4
        assertEquals(Piece.Type.SINGLE, testSpace2W.getPiece().getType());

        //Test5
        assertEquals(Piece.Color.RED, testSpace3R.getPieceColor());

        //Test6
        assertEquals(Piece.Type.SINGLE, testSpace3R.getPiece().getType());
    }


    @Test
    public void getRowIndexWorks() {

        Space tmpSpace1 = new Space(1, 0);

        //Test0
        assertNotNull(tmpSpace1.getRowIdx());

        //Test1
        assertEquals(1, tmpSpace1.getRowIdx());
    }

    @Test
    public void getCellIndexWorks() {

        Space tmpSpace1 = new Space(0, 1);

        //Test0
        assertNotNull(tmpSpace1.getCellIdx());

        //Test1
        assertEquals(1, tmpSpace1.getCellIdx());
    }

    @Test
    public void getCellIdxNotNull() {
        assertNotNull(space.getCellIdx());
    }

    @Test
    public void isValidTrue() {

        Space tmpSpace0 = new Space(0, 0);
        Space tmpSpace1 = new Space(1, 0);
        Space tmpSpace2 = new Space(2, 3);
        Space tmpSpace3 = new Space(11, 11);

        Piece piece0 = new Piece(Piece.Type.SINGLE, Piece.Color.RED);

        Space tmpSpace4 = new Space(1, 0, piece0);
        Space tmpSpace5 = new Space(0, 5, piece0);


        //Test1
        assertNotNull(tmpSpace0.isValid());

        //Test2
        assertFalse(tmpSpace0.isValid());

        //Test3
        assertTrue(tmpSpace1.isValid());

        //Test4
        assertTrue(tmpSpace2.isValid());

        //Test5
        assertFalse(tmpSpace3.isValid());

        //Test6 checking for piece not null rowIndex % 2 == 0
        assertFalse(tmpSpace4.isValid());

        //Test7 checking for piece not null rowIndex % 2 == 1
        assertFalse(tmpSpace5.isValid());
    }

    @Test
    public void isValidTrueNotNull() {
        Space testSpace = new Space(3, 1);

        assertNotNull(testSpace.isValid());
    }


    @Test
    public void getPieceWorks() {
        Piece piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Space tmp = new Space(1, 1, piece);

        //Test1
        assertNotNull(tmp.getPiece());

        //Test2
        assertEquals(piece, tmp.getPiece());
    }


    @Test
    public void pieceColorMatchWorks() {
        Piece piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Space tmp = new Space(1, 1, piece);

        Piece pieceNull = null;
        Space tmpSpace1 = new Space(10, 10, pieceNull);


        //Test1
        assertNotNull(tmp.getPiece());

        //Test2
        assertEquals(true, tmp.pieceColorMatch(Piece.Color.RED));

        //Test3
        assertEquals(false, tmp.pieceColorMatch(Piece.Color.WHITE));

        assertFalse(tmpSpace1.pieceColorMatch(Piece.Color.RED));
    }

/*
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
        assertEquals( tmp.getRowIdx() , deepCopy.getRowIdx() );

        //Test6
        assertEquals( tmp.getCellIdx() , deepCopy.getCellIdx() );

        //Test7
        assertEquals( tmp.getPieceColor() , deepCopy.getPieceColor() );
    }
*/

    @Test
    public void removePieceWorks() {
        Piece pieceR = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Space spaceR = new Space(1, 1, pieceR);

        //Test1
        assertEquals(pieceR, spaceR.removePiece());

        //Test2
        assertNull(spaceR.getPiece());
    }

    @Test
    public void hasPieceWorks() {
        Piece pieceR = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Space spaceR = new Space(1, 1, pieceR);

        Piece pieceNull = null;
        Space spaceNull = new Space(1, 1, pieceNull);

        //Test1
        assertEquals(true, spaceR.hasPiece());

        //Test2
        assertEquals(false, spaceNull.hasPiece());
    }


    @Test
    public void getPieceColorWorks() {
        Piece pieceR = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        Piece pieceW = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
        Piece pieceNull = null;

        Space spaceR = new Space(1, 1, pieceR);
        Space spaceW = new Space(1, 1, pieceW);
        Space spaceNull = new Space(1, 1, pieceNull);

        //Test1
        assertNotNull(spaceR.getPiece());

        //Test2
        assertEquals(Piece.Color.RED, spaceR.getPieceColor());

        //Test3
        assertNotEquals(Piece.Color.WHITE, spaceR.getPieceColor());

        //Test4
        assertEquals(Piece.Color.WHITE, spaceW.getPieceColor());

        //Test5
        assertNotEquals(Piece.Color.RED, spaceW.getPieceColor());

        //Test6
        assertNull(spaceNull.getPieceColor());
    }

/*
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
*/


    @Test
    public void isKingRowWorks(){
        Space s1 = new Space(0,1);
        Space s2 = new Space(7,1);
        Space s3 = new Space(2,1);

        //Test1 This checks that the returned value isn't null
        assertNotNull( s1.isKingRow() );

        //Test2 makes sure returned value is expected
        assertTrue( s1.isKingRow() );

        //Test3 makes sure returned value is expected
        assertTrue( s2.isKingRow() );

        //Test4 makes sure returned value is expected
        assertFalse( s3.isKingRow() );
    }


    @Test
    public void hasKingPieceWorks(){
        Space s0 = new Space(0,0);
        Space s1 = new Space(0,1);
        Space s2 = new Space(1,2);

        //Test1 this checks if null has a piece
        assertFalse( s0.hasKingPiece() );

        s1.initialize();

        //Test2 this checks that it has a piece but not a king
        assertFalse( s1.hasKingPiece() );

        s2.initialize();
        s2.movePieceTo( s1 );


        //Test3 this checks that it has a piece and is a king
        assertTrue( s1.hasKingPiece() );
    }


    @Test
    public void movePieceToWorks(){

        Space s1 = new Space(0,1);
        Space s2 = new Space(1,2);

        s1.initialize();

        s1.movePieceTo( s2 );

        assertTrue( s2.hasPiece() );
    }

    @Test
    public void hasPossibleMovesWorks(){
        Board board = new Board();
        board.initialize( Piece.Color.RED );
        board.initialize( Piece.Color.WHITE );

        Space s1 = board.getSpace( new Position(2,1) ); //red
        Space s2 = board.getSpace( new Position(5,2) ); //white

        //Test1 Red player Front has moves.
        assertTrue( s1.hasPossibleMoves(Piece.Color.RED, board) );

        //Test2 white player Front has moves.
        assertTrue( s2.hasPossibleMoves(Piece.Color.WHITE, board) );


        Space s3 = board.getSpace( new Position( 1,2) );//red
        Space s4 = board.getSpace( new Position( 0,1) ); //back red

        s3.movePieceTo( s4 );

        //Test3 red player king back has moves.
        assertTrue( s4.hasPossibleMoves( Piece.Color.RED, board ) );

        Space s5 = board.getSpace( new Position( 6,1) ); //white front
        Space s6 = board.getSpace( new Position( 7,0) ); //white back

        s5.movePieceTo( s6 );

        //Test4 White player king back has moves.
        assertTrue( s6.hasPossibleMoves( Piece.Color.WHITE, board ) );
    }





    @Test
    public void hasSecondJumpAvailableWorks(){


//        Board b1 = mock(Board.class );
        Board b1 = new Board();
//        Space s1 = mock( Space.class );
//        Space sRs = new Space( 0,1, new Piece( Piece.Type.SINGLE, Piece.Color.RED ) );





        //white single jumping two red pieces
//        Space r0 = b1.getSpace( new Position(4,2) );
//        Space r1 = b1.getSpace( new Position(6,4) );
//        Space w0 = b1.getSpace( new Position(6,4) );

//        r0.initialize();
//        r1.initialize();
//        w0.initialize();



//        assertTrue( s1.hasSecondJumpAvailable( Piece.Color.RED, b1, false) );

//        System.out.println( w0.hasPiece() );


//        System.out.println( b1.getSpace(new Position(0, 1) ).isValid() );









    }



    @Test
    public void toStringWorks(){
        Space space = new Space( 0, 1);
        space.initialize();

        //Test1 checking to make sure method call doesn't return a null
        assertNotNull( space.toString() );

        //Test2 makes sure the output is expected
        assertEquals( "Space{" +
                "cellIdx=" + 1 +
                ", rowIdx=" + 0 +
                '}' , space.toString() );
    }


}