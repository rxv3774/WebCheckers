package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetSpectatorGameRouteTest {
    private GetSpectatorGameRoute spectatorGameRoute;
    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    private Request request;
    private Response response;
    private Session session;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);

        when(request.session()).thenReturn(session);

        templateEngine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();
        spectatorGameRoute = new GetSpectatorGameRoute(playerLobby, templateEngine);
    }

    @Test
    void testNewSession() {
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
    }

    @Test
    void testSpectatorIsNull() {
        when(session.attribute("name")).thenReturn(null);

        assertThrows(spark.HaltException.class, () -> spectatorGameRoute.handle(request, response));
    }
}
