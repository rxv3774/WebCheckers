package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link PostCheckTurnRouteTest} component
 */
@Tag("UI-tier")
public class PostValidateMoveRouteTest {


    private static final String SESSION_NAME_ATTR = "name";

    private Gson gson;
    private PlayerLobby playerLobby;
    private Board board;

    private Request request;
    private Response response;
    private Session session;
    private Move move;

    private PostValidateMoveRoute CuT;

    private Move VALID_MOVE = new Move( new Position(2,3), new Position(3,4) );


    private final String JSON_STRING = "{\"start\":{\"row\":2,\"cell\":3},\"end\":{\"row\":3,\"cell\":4}}";


    @BeforeEach
    void setup() {

        gson = new Gson();
        playerLobby = mock( PlayerLobby.class );
        board = mock( Board.class );

        move = mock( Move.class );
        request = mock( Request.class );
        response = mock( Response.class );

        session = mock( Session.class );
        when( request.session() ).thenReturn( session );

        CuT = new PostValidateMoveRoute( gson, playerLobby );
    }


    @Test
    public void moveFromJsonWorks(){

        //Test1 checks to make sure null isn't returned
        assertNotNull( CuT.moveFromJson( JSON_STRING ) );

        //Test2 checks to see if the expected value is returned
        assertEquals( VALID_MOVE, CuT.moveFromJson( JSON_STRING ));
    }


    @Test
    public void handlePlayerIsNull(){

        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        when( playerLobby.getPlayerObject(currentPlayerName) ).thenReturn( null );

        //Test1 makes sure response isn't null
        assertNotNull( CuT.handle(request, response) );

        //Test2 makes sure returned value is what we expected
        assertEquals( gson.toJson(Message.ERR_NOT_SIGNED_IN), CuT.handle(request, response ) );
    }


    @Test
    public void handleGameIsNull(){

        Player p1 = new Player( "Mr Robot" );

        when( session.attribute( SESSION_NAME_ATTR) ).thenReturn( p1.getName() );
        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        when( playerLobby.getPlayerObject(currentPlayerName) ).thenReturn( p1 );

        //Test1 makes sure response isn't null
        assertNotNull( CuT.handle(request, response) );

        //Test2 makes sure returned value is what we expected
        assertEquals( gson.toJson(Message.ERR_NO_OPPONENT), CuT.handle(request, response ) );
    }

    @Test
    public void handleRegMove(){

        Player player = mock( Player.class );
//        Player p1 = new Player( "DeadPool" );
//        Player p2 = new Player( "Bob" );
        Player p2 = mock( Player.class );


        final String p1Name = "DeadPool";
        final String p2Name = "Bob";

        when( session.attribute( SESSION_NAME_ATTR) ).thenReturn( p1Name );
        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        when( playerLobby.getPlayerObject(currentPlayerName) ).thenReturn( player );

        Match game = mock( Match.class );

        when( player.getMatch() ).thenReturn( game );

        when( request.body() ).thenReturn( JSON_STRING );
//        when( CuT.moveFromJson(request.body() )).thenReturn( JSON_STRING );

//        doReturn(answer).when(mockedObject).methodToMock(param1, param2);
//        doReturn( move ).when( mock( PostValidateMoveRoute.class ) ).moveFromJson( request.body() );

//        when( mock( PostValidateMoveRoute.class ).moveFromJson( request.body() ) ).thenReturn( move );


        when( game.getBoard() ).thenReturn( board );



//        when( game.getRedPlayer() ).thenReturn( player );
        when( game.doPlayersMatch( game.getRedPlayer(), player) ).thenReturn( true );
        when( !game.hasPendingMoves() ).thenReturn( false );

        boolean redPlayer = true;
        when( move.isValid( board, redPlayer ) ).thenReturn( true );


//        System.out.println("one");
//        System.out.println( !game.hasPendingMoves() );
//        System.out.println("two");
//        System.out.println( move.isValid(board, redPlayer));



        //Test1 makes sure response isn't null
        assertNotNull( CuT.handle(request, response) );

        //Test2 makes sure returned value is what we expected
//        assertEquals( gson.toJson(Message.VALID_MOVE), CuT.handle(request, response ) );
    }



}