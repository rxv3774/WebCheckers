package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import javafx.beans.binding.When;
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
        playerLobby = new PlayerLobby();
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
        //Test1 makes sure response isn't null
        assertNotNull( CuT.handle(request, response) );

        //Test2 makes sure returned value is what we expected
        assertEquals( gson.toJson(Message.ERR_NOT_SIGNED_IN), CuT.handle(request, response ) );
    }


    @Test
    public void handleGameIsNull(){

        Player p1 = new Player( "Mr Robot" );
        playerLobby.addPlayer(p1);

        when( session.attribute( SESSION_NAME_ATTR) ).thenReturn( p1.getName() );

        //Test1 makes sure response isn't null
        assertNotNull( CuT.handle(request, response) );

        //Test2 makes sure returned value is what we expected
        assertEquals( gson.toJson(Message.ERR_NO_OPPONENT), CuT.handle(request, response ) );
    }

    @Test
    public void handleRegMove(){

        final String p1Name = "DeadPool";
        final String p2Name = "Bob";

        Player player1 = new Player( p1Name );
        Player player2 = new Player( p2Name );
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);

        when( session.attribute( SESSION_NAME_ATTR) ).thenReturn( p1Name );

        when( request.body() ).thenReturn(JSON_STRING);

        Match match = mock(Match.class);
        player1.playGame(match);

        boolean redPlayer = true;
        when( match.getBoard() ).thenReturn(board);
        when( match.getRedPlayer() ).thenReturn(player1);
        when( match.doPlayersMatch( player1, player1) ).thenReturn(redPlayer);

        when( match.hasPendingMoves() ).thenReturn(false);

        when( move.isValid(board, redPlayer) ).thenReturn(true);

//        assertEquals( gson.toJson(Message.VALID_MOVE), CuT.handle(request, response));
//        assertTrue(match.hasPendingMoves());

    }



}