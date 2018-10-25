package com.webcheckers.ui.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.GetHomeRoute;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * The unit test suite for the {@link GetHomeRoute} component
 */
@Tag("UI-tier")
class GetHomeRouteTester {
    private static final PlayerLobby playerLobby = new PlayerLobby();
    private static final GameCenter gameCenter = new GameCenter(new Gson());
    private static final TemplateEngine templateEngine = new FreeMarkerEngine();

    private Request request;
    private Response response;

    @Test
    void test_constructor() {
        new GetHomeRoute(playerLobby, gameCenter, templateEngine);
    }

    void test_handle() {
        final GetHomeRoute getHomeRoute = new GetHomeRoute(playerLobby, gameCenter, templateEngine);
        request = mock(Request.class);
        response = mock(Response.class);
    }
}
