package com.webcheckers.ui;


import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostResignRouteTest {


    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Gson gson;


    private PostResignRoute CuT;

    private Request request;
    private Response response;
    private Session session;

    private static final String SESSION_NAME_ATTR = "name";


    /**
     * SetUp new mock objects for each test.
     */
    @BeforeEach
    public void setup() {


        playerLobby = mock(PlayerLobby.class);
        gameCenter = mock(GameCenter.class);
        gson = new Gson();


        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);

        when(request.session()).thenReturn(session);

        CuT = new PostResignRoute(playerLobby, gameCenter, gson);
    }


    @Test
    public void handleMatchIsNull() {
        final String NAME = "Caterpillar";

        when(session.attribute(SESSION_NAME_ATTR)).thenReturn(NAME);
        String currentPlayerName = (String)session.attribute(SESSION_NAME_ATTR);
        when(playerLobby.getPlayerObject(currentPlayerName)).thenReturn(new Player(NAME));

        //Test1 This checks to make sure a null isn't returned
        assertNotNull(CuT.handle(request, response));

        //Test2 This checks to see if we get the desired response
//        assertEquals( gson.toJson( Message.PLAYER_RESIGNATION ), CuT.handle(request, response) );
    }


//    public void handleMatchIsntNull(){}


}