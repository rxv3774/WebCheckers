package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link PostSubmitTurnRoute} component
 */
@Tag("UI-tier")
class PostSubmitTurnRouteTest {

    private Gson gson;
    private PlayerLobby playerLobby;
    private Request request;
    private Response response;
    private Session session;

    private PostSubmitTurnRoute postSubmitTurnRoute;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);

        playerLobby = mock(PlayerLobby.class);
        gson = new Gson();

        postSubmitTurnRoute = new PostSubmitTurnRoute(gson, playerLobby);
    }

    @Test
    void handleCurrentPlayerNull() {
        assertEquals( gson.toJson(Message.ERR_NOT_SIGNED_IN), postSubmitTurnRoute.handle(request, response));
    }

    @Test
    void handleNoPendingMoves() {
        String name = "Billy";
        Match match = mock(Match.class);
        Player player = new Player(name);
        player.playGame(match);

        when(request.session().attribute("name")).thenReturn(name);
        when( playerLobby.getPlayerObject( name ) ).thenReturn( player );

        assertEquals( gson.toJson(Message.ERR_NO_PENDING_MOVES), postSubmitTurnRoute.handle(request, response));
    }

    @Test
    void handleDoubleJumpAvailableTest() {
        String name = "Billy";
        Match match = mock(Match.class);
        Player player = new Player(name);
        player.playGame(match);

        when(request.session().attribute("name")).thenReturn(name);
        when( playerLobby.getPlayerObject( name ) ).thenReturn( player );

        when( match.hasPendingMoves() ).thenReturn(true);
        when( match.doubleJumpAvailable() ).thenReturn(true);

        assertEquals( gson.toJson(Message.ERR_DJ_AVAILABLE), postSubmitTurnRoute.handle(request, response));

    }

    @Test
    void handleDoubleJumpNotAvailableTest() {
        String name = "Billy";
        Match match = mock(Match.class);
        Player player = new Player(name);
        player.playGame(match);

        when(request.session().attribute("name")).thenReturn(name);
        when( playerLobby.getPlayerObject( name ) ).thenReturn( player );

        when( match.hasPendingMoves() ).thenReturn(true);
        when( match.doubleJumpAvailable() ).thenReturn(false);

        assertEquals( gson.toJson(Message.MOVE_SUBMITTED), postSubmitTurnRoute.handle(request, response));

    }
}