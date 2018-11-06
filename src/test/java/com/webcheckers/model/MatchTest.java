package com.webcheckers.model;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */

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
        assertTrue(match.join(p1));

        //Test2 Two players
        assertTrue(match.join(p2));

        //Test3 adding a third player should fail
        assertFalse(match.join(p3));

        //Test4 the player is null, should be false
        assertFalse(match.join(nullPlayer));
    }

    @Test
    void getActivePlayerWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.join(p1);
        match.join(p2);

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

        match.join(p1);
        match.join(p2);

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

        match.join(p1);
        match.join(p2);

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

        match.join(p1);

        //Test1 null isn't returned
        match.ready();

        //Test2 the match isn't ready return false
        assertFalse(match.ready());

        match.join(p2);

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

        match.join(p1);

        //Test3 match doesn't start with 1 players
        assertFalse(match.start());

        match.join(p2);

        //Test4 match starts with 2 players
        assertTrue(match.start());
    }

    @Test
    void endWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.join(p1);
        match.join(p2);

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

        match.join(p1);
        match.join(p2);

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

        match.join(p1);

        //Test2 1 players so it will be false
        assertFalse(match.isRunning());

        match.join(p2);

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

        match.join(p1);
        match.join(p2);

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

        testMatch.join(redTestPlayer);
        testMatch.join(whiteTestPlayer);

        assertTrue(testMatch.matchContains(redTestPlayer));
        assertTrue(testMatch.matchContains(whiteTestPlayer));
    }
}
