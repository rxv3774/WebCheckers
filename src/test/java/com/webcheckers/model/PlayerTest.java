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
    public void isInGameWorks(){
        Match match = new Match();

        Player p1 = new Player( "Brett" );
        Player p2 = new Player( "Kevin" );
        Player p3 = new Player( "Larry" );

        match.join( p1 );
        match.join( p2 );

        match.start();

        //Test1 isInGame doesn't return a null value
        assertNotNull( p1.isInGame() );

        //Test2 should be true since they are in a match
        assertTrue( p1.isInGame() );

        //Test3 should be false since they aren't in a match
        assertFalse( p3.isInGame() );

    }
}
