package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import static org.junit.jupiter.api.AssertTrue.assertTrue;

/*
 * UI controller to post the game
 *
 * Author: TeamD
 */


@Tag("UI-tier")
class GetGameRouteTest {

    static final String VIEW_NAME = "game.ftl";
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());


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

    /**
     * Test that getGameRoute shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());


    }


}