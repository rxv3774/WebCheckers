package com.webcheckers.appl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Application-tier")
public class PlayerLobbyTester {
    private static final String VALID_NAME = "Test Name";
    private static final String INVALID_NAME = "~~~~";
    private static final String NULL_NAME = "";

    private PlayerLobby playerLobby;

    @BeforeEach
    void setup() {
        playerLobby = new PlayerLobby();
    }

    @Test
    void test_isValidName() {
        assertTrue(playerLobby.isValidName(VALID_NAME));
        assertFalse(playerLobby.isValidName(INVALID_NAME));
        assertFalse(playerLobby.isValidName(NULL_NAME));
    }
}
