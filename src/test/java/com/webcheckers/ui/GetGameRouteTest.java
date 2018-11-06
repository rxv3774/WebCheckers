package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
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
    public void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
    }


    @Test
    public void playerIsNull(){
        when( session.attribute(SESSION_ATTRIBUTE_NAME) ).thenReturn( null );

        //Test1 This throws a holt error since they are redirected.
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response) );
    }


    @Test //Player isn't in a match but the Opponent doesn't exist
    public void playerExistsNoOpponent(){

        Player p1 = new Player( "Tom" );
        playerLobby.addPlayer( p1 );

        //This is for the current Player
        when( session.attribute(SESSION_ATTRIBUTE_NAME) ).thenReturn( p1.getName() );

        //This is for the Opponent
        when( request.queryParams(SESSION_ATTRIBUTE_NAME) ).thenReturn( null );

        //Test1 catches the halt since the opponent doesn't exist
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response) );
    }


    @Test //Player isn't in a match and Opponent doesn't exist
    public void playerExistsOpponentIsPlayer(){

        Player p1 = new Player( "Tom" );
        playerLobby.addPlayer( p1 );

        //This is for the current Player
        when( session.attribute(SESSION_ATTRIBUTE_NAME) ).thenReturn( p1.getName() );

        //This is for the Opponent
        when( request.queryParams(SESSION_ATTRIBUTE_NAME) ).thenReturn( p1.getName() );

        //Test1 catches the halt since the opponent name has the same
        assertThrows(spark.HaltException.class, () -> getGameRoute.handle(request, response) );
    }

    @Test // Player isn't in a match and the Opponent is in a match
    public void playerExistsOpponentInMatch(){
        Player p1 = new Player( "Tom" );
        Player p2 = new Player( "Tom Brady" );
        Player p3 = new Player( "Adam West" );

        playerLobby.addPlayer( p1 );
        playerLobby.addPlayer( p2 );
        playerLobby.addPlayer( p3 );

        gameCenter.createGame( p2, p3);

        //This is for the current Player
        when( session.attribute(SESSION_ATTRIBUTE_NAME) ).thenReturn( p1.getName() );

        //This is for the Opponent
        when( request.queryParams(SESSION_ATTRIBUTE_NAME) ).thenReturn( p2.getName() );

        //Test1 catches the halt since the opponent name is in a match
        assertThrows( spark.HaltException.class, () -> getGameRoute.handle(request, response) );
    }



    @Test // Player isn't in a match and the Opponent isn't in a match
    public void playerExistsOpponentNotInMatch(){
        Player p1 = new Player( "Tom" );
        Player p2 = new Player( "Tom Brady" );

        playerLobby.addPlayer( p1 );
        playerLobby.addPlayer( p2 );

        //This is for the current Player
        when( session.attribute(SESSION_ATTRIBUTE_NAME) ).thenReturn( p1.getName() );

        //This is for the Opponent
        when( request.queryParams(SESSION_ATTRIBUTE_NAME) ).thenReturn( p2.getName() );

        //Test1 catches the halt since the opponent name is the same
        assertThrows( spark.HaltException.class, () -> getGameRoute.handle(request, response) );

    }

    @Test //Player is in a match
    public void playerInMatch(){
        Player p1 = new Player( "Tom" );
        Player p2 = new Player( "Tom Brady" );

        playerLobby.addPlayer( p1 );
        playerLobby.addPlayer( p2 );

        gameCenter.createGame( p1, p2);

        //This is for the current Player
        when( session.attribute(SESSION_ATTRIBUTE_NAME) ).thenReturn( p1.getName() );

        //This is for the Opponent
        when( request.queryParams(SESSION_ATTRIBUTE_NAME) ).thenReturn( p2.getName() );


        TemplateEngineTest testHelper = new TemplateEngineTest();

        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //Analyze the results
        //  * model is a null Map
        testHelper.assertViewModelDoesNotExists();

        assertNotEquals( "", getGameRoute.handle(request, response ) );
    }





}