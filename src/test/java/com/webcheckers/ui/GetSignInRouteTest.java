package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link GetSignInRoute} component
 */
@Tag("UI-tier")
class GetSignInRouteTest {
    private static final String VIEW_NAME = "signin.ftl";

    private static final String TITLE_ATTR = "title";
    private static final String PLAYER_LOBBY_KEY = "playerLobby";

    private static final String TITLE = "Sign-In";

    private GetSignInRoute getSignInRoute;
    private PlayerLobby playerLobby;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        // create a unique getSignInRoute for each test
        playerLobby = new PlayerLobby();
        getSignInRoute = new GetSignInRoute(engine);
    }

    /**
     * Test that getSignInRoute shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        getSignInRoute.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(TITLE_ATTR, TITLE);
        //   * test view name
        testHelper.assertViewName(VIEW_NAME);
        // Verify that the Player Lobby object is stored in the session
        verify(session).attribute(eq(PLAYER_LOBBY_KEY), any(PlayerLobby.class));
    }

}