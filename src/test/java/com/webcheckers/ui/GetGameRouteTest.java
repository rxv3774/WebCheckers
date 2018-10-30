package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.AssertTrue.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static spark.Spark.halt;

/*
 * UI controller to post the game
 *
 * Author: TeamD
 */


@Tag("UItier")
class GetGameRouteTest {

    static final String VIEW_NAME = "game.ftl";
    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );


    private GetGameRoute getGameRoute;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;


    @BeforeEach
    void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();
        getGameRoute = new GetGameRoute(gameCenter, playerLobby, engine);
    }




}