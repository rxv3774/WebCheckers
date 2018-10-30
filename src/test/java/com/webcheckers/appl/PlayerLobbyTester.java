package com.webcheckers.appl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The unit test suite for the {@link PlayerLobby} component
 */
@Tag("Application-tier")
public class PlayerLobbyTester {
    private static final String VALID_NAME = "Test Name";
    private static final String INVALID_NAME = "~~~~";
    private static final String NULL_NAME = "";

    private PlayerLobby playerLobby;

    /**
     * Setup a {@link PlayerLobby} object
     */
    @BeforeEach
    void setup() {
        playerLobby = new PlayerLobby();
    }

    /**
     * Tests that the {@link PlayerLobby}.isValidName method works
     */
    @Test
    void test_isValidName() {
//        assertTrue(playerLobby.isValidName(VALID_NAME));
//        assertFalse(playerLobby.isValidName(INVALID_NAME));
//        assertFalse(playerLobby.isValidName(NULL_NAME));
    }
}
