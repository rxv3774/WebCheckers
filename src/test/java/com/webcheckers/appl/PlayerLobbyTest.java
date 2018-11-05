package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link PlayerLobby} component
 */
@Tag("Application-tier")
public class PlayerLobbyTest {
    private static final String VALID_NAME = "Test Name";
    private static final String INVALID_NAME = "~~~~";
    private static final String NULL_NAME = "";
    private static final String NO_EXIST_NAME = "Tarzan";

    private PlayerLobby playerLobby;
    private Player validPlayer;

    /**
     * Setup a {@link PlayerLobby} object
     */
    @BeforeEach
    void setup() {
        playerLobby = new PlayerLobby();
        validPlayer = new Player(VALID_NAME);
    }

    @Test
    void test_addPlayer() {
        assertThrows(NullPointerException.class, () -> playerLobby.addPlayer(null));
    }

    @Test
    void test_isValidName() {
        assertTrue(playerLobby.isValidName(VALID_NAME));
        assertFalse(playerLobby.isValidName(INVALID_NAME));
        assertFalse(playerLobby.isValidName(NULL_NAME));
    }

    @Test
    void test_playerNameInUse() {
        playerLobby.addPlayer(validPlayer);
        assertTrue(playerLobby.playerNameInUse(VALID_NAME));
        assertFalse(playerLobby.playerNameInUse(NO_EXIST_NAME));
    }

    @Test
    void test_getPlayerObject() {
        assertSame(null, playerLobby.getPlayerObject(VALID_NAME));

        playerLobby.addPlayer(validPlayer);
        assertSame(validPlayer, playerLobby.getPlayerObject(VALID_NAME));

        assertSame(null, playerLobby.getPlayerObject(NO_EXIST_NAME));
    }

    @Test
    void test_getLobbySize() {
        playerLobby.addPlayer(validPlayer);
        assertSame(1, playerLobby.getLobbySize());
    }

    @Test
    void test_getPlayerNamesAsArrayList() {
        playerLobby.addPlayer(validPlayer);
        assertEquals(new ArrayList<String>() {{
            add(validPlayer.getName());
        }}, playerLobby.getPlayersNamesAsArrayList());
    }

    @Test
    void test_getPlayerNamesAsString() {
        playerLobby.addPlayer(validPlayer);
        assertEquals(String.format("Number of players signed in: %d", playerLobby.getLobbySize()),
                playerLobby.getPlayerNamesAsString(VALID_NAME));
        assertEquals(String.format("Other Players signed in: %s", validPlayer.getName()),
                playerLobby.getPlayerNamesAsString(INVALID_NAME));

        playerLobby.addPlayer(new Player(NO_EXIST_NAME));
        playerLobby.addPlayer(new Player("Chad"));

        assertEquals("Other Players signed in: " + validPlayer.getName() + ", " + "Chad", playerLobby.getPlayerNamesAsString(NO_EXIST_NAME));
    }
}
