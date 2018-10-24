package com.webcheckers.ui.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the {@link Player} component
 */
@Tag("Model-tier")
class PlayerTester {
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
}
