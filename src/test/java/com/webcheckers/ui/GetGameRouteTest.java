package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Player;

import static org.junit.jupiter.api.Assertions.*;

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

    private final String SESSION_ATTRIBUTE_NAME = "name";
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
    void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
    }


    @Test
    void playerIsNull() {
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(null);

        //Test1 This throws a holt error since they are redirected.
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response));
    }


    @Test
        //Player isn't in a match but the Opponent doesn't exist
    void playerExistsNoOpponent() {

        Player p1 = new Player("Tom");
        playerLobby.addUser(p1);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        //This is for the Opponent
        when(request.queryParams(SESSION_ATTRIBUTE_NAME)).thenReturn(null);

        //Test1 catches the halt since the opponent doesn't exist
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response));
    }


    @Test
        //Player isn't in a match and Opponent doesn't exist
    void playerExistsOpponentIsPlayer() {

        Player p1 = new Player("Tom");
        playerLobby.addUser(p1);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        //This is for the Opponent
        when(request.queryParams(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        //Test1 catches the halt since the opponent name has the same
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response));
    }

    @Test
        // Player isn't in a match and the Opponent is in a match
    void playerExistsOpponentInMatch() {
        Player p1 = new Player("Tom");
        Player p2 = new Player("Tom Brady");
        Player p3 = new Player("Adam West");

        playerLobby.addUser(p1);
        playerLobby.addUser(p2);
        playerLobby.addUser(p3);

        gameCenter.createGame(p2, p3);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        //This is for the Opponent
        when(request.queryParams(SESSION_ATTRIBUTE_NAME)).thenReturn(p2.getName());

        //Test1 catches the halt since the opponent name is in a match
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response));
    }


    @Test
        // Player isn't in a match and the Opponent isn't in a match
    void playerExistsOpponentNotInMatch() {
        Player p1 = new Player("Tom");
        Player p2 = new Player("Tom Brady");

        playerLobby.addUser(p1);
        playerLobby.addUser(p2);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        //This is for the Opponent
        when(request.queryParams(SESSION_ATTRIBUTE_NAME)).thenReturn(p2.getName());

        //Test1 catches the halt since the opponent name is the same
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response));

    }

    @Test
        //Player is in a match
    void playerInMatch() {
        Player p1 = new Player("Tom");
        Player p2 = new Player("Tom Brady");

        playerLobby.addUser(p1);
        playerLobby.addUser(p2);

        gameCenter.createGame(p1, p2);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        //This is for the Opponent
        when(request.queryParams(SESSION_ATTRIBUTE_NAME)).thenReturn(p2.getName());


        TemplateEngineTest testHelper = new TemplateEngineTest();

        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //Analyze the results
        //  * model is a null Map
        testHelper.assertViewModelDoesNotExists();

        assertNotEquals("", getGameRoute.handle(request, response));
    }

    @Test
    void playerCantPlayTest() {
        Player p1 = new Player("Tom");
        Match match = mock(Match.class);
        p1.joinGame(match);
        playerLobby.addUser(p1);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        when(match.canPlay()).thenReturn(false);

        getGameRoute.handle(request, response);

        assertNotNull(match);
    }

    @Test
    void hasWinnerTest() {
        Player p1 = new Player("Tom");
        Match match = mock(Match.class);
        p1.joinGame(match);
        playerLobby.addUser(p1);

        //This is for the current Player
        when(session.attribute(SESSION_ATTRIBUTE_NAME)).thenReturn(p1.getName());

        when(match.canPlay()).thenReturn(true);
        when(match.hasWinner()).thenReturn(true);

        when(match.isWinner(p1)).thenReturn(false);

        getGameRoute.handle(request, response);
        assertFalse(gameCenter.containsMatch(match));

    }

}