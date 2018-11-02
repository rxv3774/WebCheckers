package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * The unit test suite for the {@link GetHomeRoute} component
 *
 * @author Henry Larson
 */
@Tag("UI-tier")
class GetHomeRouteTester {
    private static final String GAME_URL = "/game";

    private static final String VIEW_NAME = "home.ftl";

    private static final String TITLE_ATTR = "title";
    private static final String MESSAGE_ATTR = "messageType";
    private static final String BUTTON_ATTR = "showGameButton";
    private static final String PLAYER_LIST_ATTR = "playerLst";
    private static final String GAME_CENTER_ATTR = "gameCenter";
    private static final String PLAYER_LOBBY_ATTR = "playerLobby";
    private static final String NAME_ATTR = "name";
    private static final String SIGNED_IN_ATTR = "signedin";

    private static final String TITLE = "Welcome!";
    private static final String MESSAGE_TYPE = "info";
    private static final String NO_PLAYERS = "There aren't any players signed in";

    private GetHomeRoute getHomeRoute;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        engine = mock(TemplateEngine.class);

        gameCenter = new GameCenter();
        playerLobby = new PlayerLobby();
        getHomeRoute = new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    @Test
    void test_constructor() {
        new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    @Test
    void test_new_session() {
        final TemplateEngineTester tester = new TemplateEngineTester();

        when(engine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());

        request.session().attribute(PLAYER_LOBBY_ATTR, playerLobby);

        getHomeRoute.handle(request, response);

        tester.assertViewModelExists();
        tester.assertViewModelIsaMap();
        // Test that the model-view contains all necessary data
        tester.assertViewModelAttribute(TITLE_ATTR, TITLE);
        tester.assertViewModelAttribute(MESSAGE_ATTR, MESSAGE_TYPE);
        tester.assertViewModelAttribute(BUTTON_ATTR, false);
        tester.assertViewModelAttribute(PLAYER_LIST_ATTR, NO_PLAYERS);
        // Test the view name
        tester.assertViewName(VIEW_NAME);

        // Verify that the Game Center object is stored in the session
        verify(session).attribute(eq(GAME_CENTER_ATTR), any(GameCenter.class));
    }

    @Test
    void test_old_session() {
        final TemplateEngineTester tester = new TemplateEngineTester();

        when(session.attribute(PLAYER_LOBBY_ATTR)).thenReturn(session.attribute(PLAYER_LOBBY_ATTR));

        getHomeRoute.handle(request, response);

        tester.assertViewModelExists();
        tester.assertViewModelIsaMap();
        // Test that the model-view contains all necessary data
        tester.assertViewModelAttribute(TITLE_ATTR, TITLE);
        tester.assertViewModelAttribute(MESSAGE_ATTR, MESSAGE_TYPE);

        if (playerLobby.getLobbySize() > 0) {
            tester.assertViewModelAttribute(SIGNED_IN_ATTR, true);
            tester.assertViewModelAttribute(PLAYER_LIST_ATTR, playerLobby
                    .getPlayersNames()
                    .remove(session.attribute(NAME_ATTR))
            );
        }

        if (playerLobby.getLobbySize() > 1) {
            tester.assertViewModelAttribute(BUTTON_ATTR, true);
            /*
            if (gameCenter.containsPlayer(session.attribute(NAME_ATTR))) {
                verify(response).redirect(GAME_URL);
            }
            */
        }

        // Test the view name
        tester.assertViewName(VIEW_NAME);

        // Verify that the current player's name, Game Center, and Player Lobby objects are stored in the session
        verify(session).attribute(eq(GAME_CENTER_ATTR), any(GameCenter.class));
        verify(session).attribute(eq(PLAYER_LOBBY_ATTR), any(PlayerLobby.class));
        verify(session).attribute(eq(NAME_ATTR), any(String.class));
    }
}
