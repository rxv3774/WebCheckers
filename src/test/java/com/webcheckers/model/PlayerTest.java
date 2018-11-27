package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link Player} component
 *
 * @author Henry Larson
 */
@Tag("Model-tier")
class PlayerTest {
    private static final String TEST_PLAYER_NAME = "TestPlayer";

    /**
     * Tests that the constructor functions correctly
     */
    @Test
    void test_constructor() {
        final Player player = new Player(TEST_PLAYER_NAME);
        assertEquals(player.toString(), "{Player: " + TEST_PLAYER_NAME + "}");
    }

    /**
     * Tests that {@link Player#getName()} functions correctly
     */
    @Test
    void test_getName() {
        final Player player = new Player(TEST_PLAYER_NAME);
        assertEquals(player.getName(), TEST_PLAYER_NAME);
    }

    @Test
    public void isInGameWorks() {
        Match match = new Match();

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");
        Player p3 = new Player("Larry");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1 isInGame doesn't return a null value
        assertNotNull(p1.isInGame());

        //Test2 should be true since they are in a match
        assertTrue(p1.isInGame());

        //Test3 should be false since they aren't in a match
        assertFalse(p3.isInGame());
    }


    @Test
    public void playGameWorks() {
        Match match = new Match();
        Match nullMatch = null;

        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        //Test1 joinGame doesn't return a null
        assertNotNull(p1.joinGame(match));

        //Test2 joinGame has a valid match so it should return false
        assertFalse(p1.joinGame(match));

        //Test3 joinGame has a valid match so it should return true
        assertFalse(p1.joinGame(nullMatch));
    }

    @Test
    public void equalsWorks() {
        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");
        Player p3 = new Player("Kevin");

        Board board = new Board();

        //Test1 board is not a player object will fail
        assertFalse(p1.equals(board));

        //Test2 different names will fail
        assertFalse(p1.equals(p2));

        //Test3 same name so it will be true
        assertTrue(p2.equals(p3));
    }

    @Test
    public void getViewModeWorks() {
        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");

        Player p3 = new Player("Batman");

        Match match = new Match();

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1 doesn't return a null value
        assertNotNull(p1.getViewMode());

        //Test2 p1 is a player
        assertEquals("PLAY", p1.getViewMode());

        //Test3 p3 is a Spectator
        assertEquals("SPECTATOR", p3.getViewMode());
    }


    @Test
    public void endGameWorks() {
        Player p1 = new Player("Brett");
        Player p2 = new Player("Kevin");
        Player p3 = new Player("Batman");

        Match match = new Match();

        match.joinPlayer(p1);
        match.joinPlayer(p2);

        match.start();

        //Test1 p1 does have a match
        assertNotNull(p1.getMatch());

        p1.endGame();
        p3.endGame();

        //Test2 p1 doesn't have a match anymore
        assertNull(p1.getMatch());

        //Test3 never had a match so it's still null
        assertNull(p3.getMatch());
    }


    @Test
    public void GamesWonWorks() {
        Player p1 = new Player("FRED");

        //Test1 The player should have 0 wins
        assertEquals(0, p1.getGamesWon());

        p1.increaseGamesWon();

        //Test2 The player should have 1 wins
        assertEquals(1, p1.getGamesWon());
    }


    @Test
    public void GamesDrawedWorks() {
        Player p1 = new Player("FRED");

        //Test1 The player should have 0 draws
        assertEquals(0, p1.getGamesTied());

        p1.increaseGamesTied();

        //Test2 The player should have 1 draws
        assertEquals(1, p1.getGamesTied());
    }


    @Test
    public void GamesLostWorks() {
        Player p1 = new Player("FRED");

        //Test1 The player should have 0 lost
        assertEquals(0, p1.getGamesLost());

        p1.increaseGamesLost();

        //Test2 The player should have 1 lost
        assertEquals(1, p1.getGamesLost());
    }


}