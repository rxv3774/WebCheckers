package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        assertTrue(playerLobby.playerNameInUse(VALID_NAME) );
        assertFalse(playerLobby.playerNameInUse( NO_EXIST_NAME ) );
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
        assertEquals(new ArrayList<String>(){{add(validPlayer.getName());}}, playerLobby.getPlayersNamesAsArrayList());
    }

    @Test
    void test_getPlayerNamesAsString() {
        playerLobby.addPlayer(validPlayer);
        assertEquals(String.format("Number of players signed in: %d", playerLobby.getLobbySize()),
                playerLobby.getPlayerNamesAsString(VALID_NAME));
        assertEquals(String.format("Other Players signed in: %s", validPlayer.getName()),
                playerLobby.getPlayerNamesAsString(INVALID_NAME));

        playerLobby.addPlayer( new Player( NO_EXIST_NAME ) );
        playerLobby.addPlayer( new Player( "Chad" ) );

        assertEquals( "Other Players signed in: " + validPlayer.getName()+ ", " + "Chad", playerLobby.getPlayerNamesAsString( NO_EXIST_NAME ) );
    }

//    @Test
//    public void addPlayerAddsPlayer() {
//        final String NAME = "Kevin";
//
//        when(request.queryParams(any(String.class))).thenReturn(NAME);
//        playerLobby.addPlayer(new Player(NAME));
//
//        assertEquals(1, playerLobby.getLobbySize());
//
//        final String NAME2 = "Chad";
//
//        when(request.queryParams(any(String.class))).thenReturn(NAME2);
//        playerLobby.addPlayer(new Player(NAME2));
//
//        assertEquals(2, playerLobby.getLobbySize());
//
//    }

//    @Test
//    public void isValidWorks() {
//
//        final String NAME0 = "";
//        final String NAME1 = ")(*&^%$#@#!";
//        final String NAME2 = "Kevin";
//        final String NAME3 = "123";
//
//        //test1
//        when(request.queryParams(any(String.class))).thenReturn(NAME0);
//        playerLobby.addPlayer(new Player(NAME0));
//
//        assertEquals(0, playerLobby.getLobbySize());
//
//        //test2
//        when(request.queryParams(any(String.class))).thenReturn(NAME1);
//        playerLobby.addPlayer(new Player(NAME1));
//
//        assertEquals(0, playerLobby.getLobbySize());
//
//        //test3
//        when(request.queryParams(any(String.class))).thenReturn(NAME2);
//        playerLobby.addPlayer(new Player(NAME2));
//
//        assertEquals(1, playerLobby.getLobbySize());
//
//        //test3
//        when(request.queryParams(any(String.class))).thenReturn(NAME3);
//        playerLobby.addPlayer(new Player(NAME3));
//
//        assertEquals(2, playerLobby.getLobbySize());
//
//    }

//    @Test
//    public void playerNameInUseWorks() {
//        final String NAME0 = "Kevin";
//        final String NAME1 = "123";
//
//
//        when(request.queryParams(any(String.class))).thenReturn(NAME0);
//        playerLobby.addPlayer(new Player(NAME0));
//
//        //test1
//        assertEquals(true, playerLobby.playerNameInUse(NAME0));
//
//        //test2
//        assertEquals(false, playerLobby.playerNameInUse(NAME1));
//    }

//    @Test
//    public void getPlayerNamesWorks() {
//
//        final String NAME0 = "Kevin";
//        final String NAME1 = "123";
//
//        ArrayList<String> arr = new ArrayList<>();
//
//        arr.add(NAME0);
//        arr.add(NAME1);
//
//        when(request.queryParams(any(String.class))).thenReturn(NAME0);
//        playerLobby.addPlayer(new Player(NAME0));
//
//        when(request.queryParams(any(String.class))).thenReturn(NAME1);
//        playerLobby.addPlayer(new Player(NAME1));
//
//        //Test1
//        assertNotNull(playerLobby.getPlayersNamesAsArrayList());
//
//        //Test2
//        assertEquals(arr, playerLobby.getPlayersNamesAsArrayList());
//    }

//    @Test
//    public void getPlayerObjectWorks() {
//
//        final String[] arr = {"Kevin", "123", "456", "789", "Brad"};
//
//        for (String name : arr) {
//            when(request.queryParams(any(String.class))).thenReturn(name);
//            playerLobby.addPlayer(new Player(name));
//        }
//
//        //Test1
//        assertNotNull(playerLobby.getPlayerObject(arr[0]));
//
//        //Test2
//        assertEquals(new Player(arr[0]), playerLobby.getPlayerObject(arr[0]));
//    }

//    @Test
//    public void getLobbySizeWorks() {
//        final String[] arr = {"Kevin", "123", "456", "789", "Brad"};
//
//        for (String name : arr) {
//            when(request.queryParams(any(String.class))).thenReturn(name);
//            playerLobby.addPlayer(new Player(name));
//        }
//
//        assertEquals(arr.length, playerLobby.getLobbySize());
//    }


//    @Test
//    public void getPlayerNameListNotNull() {
//        final String NAME = "Kevin";
//
//        when(request.queryParams(any(String.class))).thenReturn(NAME);
//        playerLobby.addPlayer(new Player(NAME));
//
//
//        assertNotNull(playerLobby.getPlayerNamesAsString(NAME));
//    }
}
