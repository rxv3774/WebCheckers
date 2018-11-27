package com.webcheckers.ui;


import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link PostBackUpMoveRoute} component
 */
@Tag("UI-tier")
public class PostBackUpMoveRouteTest {

    private Gson gson;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private Request request;
    private Response response;
    private Session session;
    private Match match;

    PostBackUpMoveRoute CuT;

    private static final String SESSION_NAME_ATTR = "name";

    @BeforeEach
    public void setup() {

        gson = new Gson(); // can't make a mock object of Gson

        gameCenter = mock(GameCenter.class);
        playerLobby = mock(PlayerLobby.class);
        match = mock(Match.class);

        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);

        when(request.session()).thenReturn(session);

        CuT = new PostBackUpMoveRoute(gson, gameCenter, playerLobby);
    }

    @Test
    public void handleWorks() {

        final String NAME = "JEFF";
        Player player = mock(Player.class);

        when( session.attribute( SESSION_NAME_ATTR ) ).thenReturn( NAME );
        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        when( playerLobby.getUserObject( currentPlayerName ) ).thenReturn( player );


        when(player.getMatch()).thenReturn(match);


        //Test1 This checks to make sure the handle doesn't return a null.
        assertNotNull(CuT.handle(request, response));

        //Test2 This checks to make sure it returns the correct value.
        assertEquals( gson.toJson(Message.BACKUP_MOVE) , CuT.handle( request, response) );
    }

}