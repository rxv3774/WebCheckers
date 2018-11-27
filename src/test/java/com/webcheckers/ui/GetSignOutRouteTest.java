package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
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

/**
 * The unit test suite for the {@link GetSignOutRoute} component
 */
@Tag("UI-tier")
class GetSignOutRouteTest {

    private Request request;
    private Response response;
    private Session session;

    private GetSignOutRoute getSignOutRoute;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        playerLobby = new PlayerLobby();
        gameCenter = mock(GameCenter.class);

        // create a unique getSignOutRoute for each test
        getSignOutRoute = new GetSignOutRoute(playerLobby, gameCenter);
    }

    @Test
    void handleCurrentPlayerNull() {
        assertNull(getSignOutRoute.handle(request, response));
    }

    @Test
    void handlePlayerAtHome() {
        String name = "Billy";
        Player player = new Player(name);
        playerLobby.addUser(player);

        when(session.attribute("name")).thenReturn(name);

        getSignOutRoute.handle(request, response);

        assertNull(playerLobby.getUserObject(name));
    }

    @Test
    void handlePlayerInMatch() {
        String name = "Billy";
        Match match = new Match();
        Player player = new Player(name);
        player.joinGame(match);
        playerLobby.addUser(player);

        when(request.session().attribute("name")).thenReturn(name);

        getSignOutRoute.handle(request, response);

        assertFalse(player.isInGame());

    }

}