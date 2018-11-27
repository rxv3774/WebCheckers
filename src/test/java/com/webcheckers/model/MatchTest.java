package com.webcheckers.model;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */

import com.webcheckers.appl.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-tier")
public class MatchTest {
    private Player redPlayer;
    private Player whitePlayer;
    private TemplateEngine engine;
    private Match match;

    private Move pendingMove, DJsecondPendingMove;
    private boolean running;


    @BeforeEach
    void setup() {
        engine = mock(TemplateEngine.class);
        redPlayer = mock(Player.class);
        whitePlayer = mock(Player.class);

        match = mock(Match.class);
    }

    @Test
    void constructorNotNull() {
        new Match();
    }


    @Test
    void joinWorks() {
        Match match = new Match();
        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");
        Player p3 = new Player("Chad");
        Player nullPlayer = null;

        //Test1 one player
        assertTrue(match.joinPlayer(p1));

        //Test2 Two players
        assertTrue(match.joinPlayer(p2));

        //Test3 adding a third player should fail
        assertFalse(match.joinPlayer(p3));

        //Test4 the player is null, should be false
        assertFalse(match.joinPlayer(nullPlayer));
    }

    @Test
    void addPendingMoveWorks() {
        Match match = new Match();

        assertFalse(match.hasPendingMoves());

        Move move1 = new Move(new Position(0,0), new Position(1,1));
        match.addPendingMove(move1);

        assertTrue(match.hasPendingMoves());

        Move move2 = new Move(new Position(1,1), new Position(2,2));
        match.addPendingMove(move2);

        assertTrue(match.hasPendingDJMoves());
    }

    @Test
    void removePendingMoveWorks() {
        Match match = new Match();

        Move move1 = new Move(new Position(0,0), new Position(1,1));
        Move move2 = new Move(new Position(1,1), new Position(2,2));
        match.addPendingMove(move1);
        match.addPendingMove(move2);

        assertTrue(match.hasPendingDJMoves());
        assertTrue(match.hasPendingMoves());

        match.removePendingMove();

        assertFalse(match.hasPendingDJMoves());

        match.removePendingMove();

        assertFalse(match.hasPendingMoves());
    }

    @Test
    void doubleJumpAvailableWorks() {
        Match match = new Match();

        Move move1 = new Move(new Position(0,0), new Position(1,1));
        match.addPendingMove(move1);

        assertFalse(match.doubleJumpAvailable());
    }

    @Test
    void doPendingMovesTest(){
        Match match = new Match();
        match.getBoard().initialize(Piece.Color.RED);

        Move move1 = new Move(new Position(2,3), new Position(3,4));
        match.addPendingMove(move1);
        match.doPendingMoves();

        assertTrue(match.getBoard().spaceHasPiece(new Position(3, 4)));

        Move move2 = new Move(new Position(2,1), new Position(3,0));
        Move move3 = new Move(new Position(3,0), new Position(4,1));
        match.addPendingMove(move2);
        match.addPendingMove(move3);
        match.doPendingMoves();

        assertTrue(match.getBoard().spaceHasPiece(new Position(4, 1)));
    }

    @Test
    void canPlayTest() {
        Match match = new Match();
        match.getBoard().initialize(Piece.Color.RED);

        assertTrue(match.canPlay());
    }

    @Test
    void declareWinnerTest(){
        Match match = new Match();
        Player red = new Player("redBoi");
        Player white = new Player("whiteBoi");
        match.joinPlayer(red);
        match.joinPlayer(white);

        match.start();
        match.declareWinner();

        assertTrue(match.isWinner(white));

        match.start();
        match.changeActivePlayer();
        match.declareWinner();

        assertTrue(match.isWinner(red));

    }

    @Test
    void hasWinnerTest() {
        Match match = new Match();
        Player red = new Player("redBoi");
        Player white = new Player("whiteBoi");
        match.joinPlayer(red);
        match.joinPlayer(white);

        match.start();
        match.declareWinner();

        assertTrue(match.hasWinner());

    }

    @Test
    void doPlayersMatchTest() {
        Match match = new Match();
        Player red = new Player("redBoi");
        Player white = new Player("whiteBoi");

        assertFalse(match.doPlayersMatch(red, white));
    }

    @Test
    void getActivePlayerWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1 not null
        assertNotNull(match.getActivePlayer());

        //Test2 the active player is correct
        assertEquals(p1, match.getActivePlayer());

        //Test3 the active player is incorrect
        assertNotEquals(p2, match.getActivePlayer());
    }

    @Test
    void getRedPlayerWorks() {

        when(match.getRedPlayer()).thenReturn(redPlayer);

        //Test1
        assertNotNull(match.getRedPlayer());

        //Test2
        assertEquals(redPlayer, match.getRedPlayer());
    }

    @Test
    void getWhitePlayerWorks() {
        when(match.getWhitePlayer()).thenReturn(whitePlayer);

        //Test1
        assertNotNull(match.getWhitePlayer());

        //Test2
        assertEquals(whitePlayer, match.getWhitePlayer());
    }


    @Test
    void getActiveColorWorks() {

        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1
        assertNotNull(match.getActiveColor());

        //Test2
        assertEquals(Piece.Color.RED, match.getActiveColor());

        match.changeActivePlayer();

        //Test3
        assertEquals(Piece.Color.WHITE, match.getActiveColor());

    }


    @Test
    void getOpponentWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        //Test1 not null
        assertNotNull(match.getOpponent(p1));

        //Test2 gets white player
        assertEquals(p2, match.getOpponent(p1));

        //Test3 gets red player
        assertEquals(p1, match.getOpponent(p2));
    }


    @Test
    void getBoardWorks() {

        Match match = new Match();

        //Test1 board isn't null
        assertNotNull(match.getBoard());
    }

    @Test
    void readyWorks() {

        Match match = new Match();
        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);

        //Test1 null isn't returned
        match.ready();

        //Test2 the match isn't ready return false
        assertFalse(match.ready());

        match.joinPlayer(p2);

        //Test3 the match is ready so return true
        assertTrue(match.ready());
    }

    @Test
    void startWorks() {

        Match match = new Match();
        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        //Test1 start doesn't return null
        match.start();

        //Test2 match doesn't start with 0 players
        assertFalse(match.start());

        match.joinPlayer(p1);

        //Test3 match doesn't start with 1 players
        assertFalse(match.start());

        match.joinPlayer(p2);

        //Test4 match starts with 2 players
        assertTrue(match.start());
    }

    @Test
    void endWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1 the match is running
        assertTrue(match.isRunning());

        match.end();

        //Test2 match isn't running anymore
        assertFalse(match.isRunning());
    }

    @Test
    void closeWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        //Test 1 and 2 exist to prove that the players know the match they are in
        assertNotNull(p1.getMatch());
        assertNotNull(p2.getMatch());

        match.start();
        match.end();

        match.close();

        //Test3 the redPlayer is null
        assertNull(match.getRedPlayer());

        //Test4 the whitePlayer is null
        assertNull(match.getWhitePlayer());

        //Test5 p1 match is now null
        assertNull(p1.getMatch());

        //Test6 p2 match is now null
        assertNull(p2.getMatch());
    }

    @Test
    void isRunningWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        //Test1 0 players so it will be false
        assertFalse(match.isRunning());

        match.joinPlayer(p1);

        //Test2 1 players so it will be false
        assertFalse(match.isRunning());

        match.joinPlayer(p2);

        //Test3 2 players but the match hasn't started so it will be false
        assertFalse(match.isRunning());

        match.start();

        //Test4 2 players and the match has been started so it will be true
        assertTrue(match.isRunning());
    }

    @Test
    void changeActivePlayerWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1
        assertNotNull(match.getActiveColor());

        //Test2
        assertEquals(Piece.Color.RED, match.getActiveColor());

        match.changeActivePlayer();

        //Test3
        assertEquals(Piece.Color.WHITE, match.getActiveColor());

        match.changeActivePlayer();

        //Test4
        assertEquals(Piece.Color.RED, match.getActiveColor());

    }

    @Test
    void test_matchContains() {
        final Match testMatch = new Match();
        final Player redTestPlayer = new Player("Red");
        final Player whiteTestPlayer = new Player("White");

        testMatch.joinPlayer(redTestPlayer);
        testMatch.joinPlayer(whiteTestPlayer);

        assertTrue(testMatch.matchContains(redTestPlayer));
        assertTrue(testMatch.matchContains(whiteTestPlayer));
    }


    @Test
    public void isWinnerWorks(){
        Player p1 = new Player( "Snoopy");
        Player p2 = new Player( "Charlie");

        GameCenter gameCenter = new GameCenter();
        Match match = gameCenter.createGame( p1, p2);

        match.start();
        match.declareWinner();

        //Test this shows the other player wins
        assertTrue( match.isWinner( p2 ) );


        Player p3 = new Player( "Agent Cooper");
        Player p4 = new Player( "Danno");

        GameCenter gC = new GameCenter();
        Match m = gC.createGame( p3, p4);

        m.start();

        m.changeActivePlayer(); //simulates a move made

        m.declareWinner();

        //Test2 this hows the other player wins
        assertTrue( m.isWinner( p3 ) );
    }


    @Test
    public void dJAvailiableWorks(){
        GameCenter gameCenter = new GameCenter();

        Player p1 = new Player( "Archie" );
        Player p2 = new Player( "JugHead" );

        Match match = gameCenter.createGame( p1, p2 );

        match.start();

        match.addPendingMove( new Move( new Position(2,1), new Position( 3,2) ) );
        match.doPendingMoves();

        match.addPendingMove( new Move( new Position(1,2), new Position( 2,1) ) );
        match.doPendingMoves();

        match.addPendingMove( new Move( new Position(2,5), new Position( 3,4) ) );
        match.doPendingMoves();

        match.addPendingMove( new Move( new Position(3,4), new Position( 4,5) ) );
        match.doPendingMoves();

        match.changeActivePlayer();

        match.addPendingMove( new Move( new Position(5,6), new Position( 3,4) ) );

        assertTrue( match.doubleJumpAvailable() );
    }


    @Test
    public void getPendingMoveWorks(){
        GameCenter gameCenter = new GameCenter();

        Player p1 = new Player( "Archie" );
        Player p2 = new Player( "JugHead" );

        Match match = gameCenter.createGame( p1, p2 );

        match.start();

        Move move = new Move( new Position(2,1), new Position( 3,2) );

        match.addPendingMove( move );

        //Test1 This checks to make sure what we get is what we expected
        assertEquals( move, match.getPendingMove() );
    }


}
