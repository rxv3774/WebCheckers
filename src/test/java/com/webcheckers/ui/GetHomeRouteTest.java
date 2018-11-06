package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * The unit test suite for the {@link GetHomeRoute} component
 *
 * @author Henry Larson
 */
@Tag("UI-tier")
class GetHomeRouteTest {
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
    private static final String PLAYER_LIST = "playerLst";
    private static final String ERROR_ATTR = "errorMessage";
    private static final String SHOW_BUTTON_ATTR = "showGameButton";

    private static final String TITLE = "Welcome!";
    private static final String MESSAGE_TYPE = "info";
    private static final String NO_PLAYERS = "There aren't any players signed in";
    private static final String ERROR_MESSAGE = "errorMessage";

    private static final String VALID_NAME_ONE = "Test Name One";
    private static final String VALID_NAME_TWO = "Test Name Two";

    private GameCenter gameCenter;
    private PlayerLobby playerLobby;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    private GetHomeRoute getHomeRoute;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        gameCenter = new GameCenter();
        playerLobby = new PlayerLobby();

        getHomeRoute = new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    @Test
    void test_validConstructor() {
        new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    @Test
    void test_invalidConstructor() {
        assertThrows(NullPointerException.class, () -> {
            new GetHomeRoute(playerLobby, gameCenter, null);
        });
    }

    @Test
    void handleWorks() {
        when(request.session()).thenReturn(session);
    }

    @Test
    void test_lobbySizeZero() {
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        getHomeRoute.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewName(VIEW_NAME);

        testHelper.assertViewModelAttribute(PLAYER_LIST_ATTR, "There aren't any players signed in");
    }

    @Test
    void test_lobbySizeOneNotSignedIn() {
        playerLobby.addPlayer(new Player(VALID_NAME_ONE));

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        getHomeRoute.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewName(VIEW_NAME);

        testHelper.assertViewModelAttribute(PLAYER_LIST_ATTR, "The number of players signed in is: 1");
        testHelper.assertViewModelAttribute(SHOW_BUTTON_ATTR, false);
    }

    @Test
    void test_lobbySizeOneSignedIn() {
        final Player testPlayer = new Player(VALID_NAME_ONE);
        playerLobby.addPlayer(testPlayer);

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        getHomeRoute.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewName(VIEW_NAME);

        when(session.attribute(NAME_ATTR)).thenReturn(VALID_NAME_ONE);

        testHelper.assertViewModelAttribute(PLAYER_LIST, "The number of players signed in is: " + playerLobby.getLobbySize() );
    }
    void test_lobbySizeOneErrorMsg() {
        final String errorMsg = "This is an error message";

        final Player testPlayer = new Player(VALID_NAME_ONE);
        playerLobby.addPlayer(testPlayer);

        final TemplateEngineTest testHelper = new TemplateEngineTest();

        getHomeRoute.handle(request, response);

        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(ERROR_ATTR)).thenReturn(errorMsg);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewName(VIEW_NAME);

        testHelper.assertViewModelAttribute(ERROR_MESSAGE, "ERROR: " + errorMsg);
        testHelper.assertViewModelAttribute(SHOW_BUTTON_ATTR, false);
    }

    @Test
    void test_lobbySizeMoreThanOneNoGame() {
        final Player testPlayer1 = new Player(VALID_NAME_ONE);
        final Player testPlayer2 = new Player(VALID_NAME_TWO);
        playerLobby.addPlayer(testPlayer1);
        playerLobby.addPlayer(testPlayer2);

        final TemplateEngineTest testHelper = new TemplateEngineTest();

        when( session.attribute(NAME_ATTR) ).thenReturn( testPlayer2.getName() );
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        getHomeRoute.handle(request, response);


        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewName(VIEW_NAME);

        testHelper.assertViewModelAttribute(SHOW_BUTTON_ATTR, true);
    }
}
