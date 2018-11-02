package com.webcheckers.appl;

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
public class PlayerLobbyTester {
    private static final String VALID_NAME = "Test Name";
    private static final String INVALID_NAME = "~~~~";
    private static final String NULL_NAME = "";

    private PlayerLobby playerLobby;
    private Request request;
    private Response response;
    private Session session;

    /**
     * Setup a {@link PlayerLobby} object
     */
    @BeforeEach
    void setup() {
        playerLobby = new PlayerLobby();

        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
//        engine = mock(TemplateEngine.class);
        response = mock(Response.class);
    }

    @Test
    void test_addPlayer() {

    }

    @Test
    void test_isValidName() {
        assertTrue(playerLobby.isValidName(VALID_NAME));
        assertFalse(playerLobby.isValidName(INVALID_NAME));
        assertFalse(playerLobby.isValidName(NULL_NAME));
    }


    @Test
    public void addPlayerAddsPlayer(){
        final String NAME = "Kevin";

        when( request.queryParams(any(String.class) ) ).thenReturn( NAME );
        playerLobby.addPlayer( new Player( NAME ) );

        assertTrue( playerLobby.getLobbySize() == 1);

        final String NAME2 = "Chad";

        when( request.queryParams(any(String.class) ) ).thenReturn( NAME2 );
        playerLobby.addPlayer( new Player( NAME2 ) );

        assertEquals(2, playerLobby.getLobbySize());

    }

    @Test
    public void isValidWorks(){

        final String NAME0 = "";
        final String NAME1 = ")(*&^%$#@#!";
        final String NAME2 = "Kevin";
        final String NAME3 = "123";

        //test1
        when( request.queryParams(any(String.class) ) ).thenReturn( NAME0 );
        playerLobby.addPlayer( new Player( NAME0 ) );

        assertEquals( 0, playerLobby.getLobbySize() );

        //test2
        when( request.queryParams(any(String.class) ) ).thenReturn( NAME1 );
        playerLobby.addPlayer( new Player( NAME1 ) );

        assertEquals( 0, playerLobby.getLobbySize() );

        //test3
        when( request.queryParams(any(String.class) ) ).thenReturn( NAME2 );
        playerLobby.addPlayer( new Player( NAME2 ) );

        assertEquals( 1, playerLobby.getLobbySize() );

        //test3
        when( request.queryParams(any(String.class) ) ).thenReturn( NAME3 );
        playerLobby.addPlayer( new Player( NAME3 ) );

        assertEquals( 2, playerLobby.getLobbySize() );

    }

    @Test
    public void playerNameInUseWorks(){
        final String NAME0 = "Kevin";
        final String NAME1 = "123";


        when( request.queryParams(any(String.class) ) ).thenReturn( NAME0 );
        playerLobby.addPlayer( new Player( NAME0 ) );

        //test1
        assertEquals( true, playerLobby.playerNameInUse( NAME0 ) );

        //test2
        assertEquals( false, playerLobby.playerNameInUse( NAME1 ) );
    }

    @Test
    public void getPlayerNamesWorks(){

        final String NAME0 = "Kevin";
        final String NAME1 = "123";

        ArrayList<String> arr = new ArrayList<>();

        arr.add( NAME0 );
        arr.add( NAME1 );

        when( request.queryParams(any(String.class) ) ).thenReturn( NAME0 );
        playerLobby.addPlayer( new Player( NAME0 ) );

        when( request.queryParams(any(String.class) ) ).thenReturn( NAME1 );
        playerLobby.addPlayer( new Player( NAME1 ) );

        //Test1
        assertNotNull( playerLobby.getPlayersNames() );

        //Test2
        assertEquals( arr, playerLobby.getPlayersNames() );
    }

    @Test
    public void getPlayerObjectWorks(){

        final String [] arr = {"Kevin", "123", "456", "789", "Brad"};

        for( String name : arr){
            when( request.queryParams(any(String.class) ) ).thenReturn( name );
            playerLobby.addPlayer( new Player( name ) );
        }

        //Test1
        assertNotNull( playerLobby.getPlayerObject( arr[0] ) );

        //Test2
        assertEquals( new Player( arr[0] ), playerLobby.getPlayerObject( arr[0] ) );
    }

    @Test
    public void getLobbySizeWorks(){
        final String [] arr = {"Kevin", "123", "456", "789", "Brad"};

        for( String name : arr){
            when( request.queryParams(any(String.class) ) ).thenReturn( name );
            playerLobby.addPlayer( new Player( name ) );
        }

        assertEquals( arr.length, playerLobby.getLobbySize() );
    }


    @Test
    public void getPlayerNamelstNotNull(){
        final String NAME = "Kevin";

        when( request.queryParams(any(String.class) ) ).thenReturn( NAME );
        playerLobby.addPlayer( new Player( NAME ) );


        assertNotNull( playerLobby.getPlayerNameLst( NAME ) );
    }
}
