package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Spectator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class PostSpectatorCheckTurnRouteTest {
    private PostSpectatorCheckTurnRoute spectatorCheckTurnRoute;
    private PlayerLobby playerLobby;
    private Gson gson;

    private Request request;
    private Response response;
    private Session session;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);

        when(request.session()).thenReturn(session);

        playerLobby = mock(PlayerLobby.class);
        gson = new Gson();
        spectatorCheckTurnRoute = new PostSpectatorCheckTurnRoute(gson, playerLobby);
    }

    @Test
    void testSpectatorIsNull() {
        when(session.attribute("name")).thenReturn("Test Name");
        when(playerLobby.getUserObject("Test Name")).thenReturn(null);

        assertNull(spectatorCheckTurnRoute.handle(request, response));
    }

    @Test
    void testMatchIsNull() {
        Spectator testSpectator = new Spectator("Test Name");

        when(session.attribute("name")).thenReturn(testSpectator.getName());
        when(playerLobby.getUserObject("Test Name")).thenReturn(testSpectator);
        //when(testSpectator.getMatch()).thenReturn(null);

        assertEquals(gson.toJson(new Message("true", Message.Type.info)), spectatorCheckTurnRoute.handle(request, response));
    }

    @Test
    void testNoColorChange() {
        Spectator testSpectator = new Spectator("Test Name");
        testSpectator.joinGame(new Match());

        when(session.attribute("name")).thenReturn(testSpectator.getName());
        when(playerLobby.getUserObject("Test Name")).thenReturn(testSpectator);

        assertEquals(gson.toJson(new Message("false", Message.Type.info)), spectatorCheckTurnRoute.handle(request, response));
    }

    @Test
    void testColorChange() {
        Spectator testSpectator = new Spectator("Test Name");
        testSpectator.joinGame(new Match());

        when(session.attribute("name")).thenReturn(testSpectator.getName());
        when(playerLobby.getUserObject("Test Name")).thenReturn(testSpectator);


        assertEquals(gson.toJson(new Message("false", Message.Type.info)), spectatorCheckTurnRoute.handle(request, response));
    }
}
