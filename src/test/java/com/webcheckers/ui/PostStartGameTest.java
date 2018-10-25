package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.AssertTrue.assertTrue;
import static org.mockito.Mockito.mock;
import static spark.Spark.halt;

/*
 * UI controller to post the game
 *
 * Author: TeamD
 */


@Tag("UItier")
public class PostStartGameTest{



    static final String VIEW_NAME = "game.ftl";
    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );


    private PostStartGame postStartGame;
    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    private Request request;
    private Response response;
//    private Session session;
//    private TemplateEngine engine;




    @BeforeEach
    void setup() {
//        request = new Request();
//        request = mock(Request.class);
        response = mock(Response.class);
//        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();
        postStartGame = new PostStartGame(playerLobby, templateEngine);
    }


    @Test
    public void constructorNotNull() {
        assertNotNull( new PostStartGame(playerLobby, templateEngine) );
    }




//    @Test
//    public void handleIsNull(){
//        assertNotNull( postStartGame.handle(request, response) );
//    }



//    @Test
//    public void handleIsNull(){
//        assertNull( postStartGame.handle(request, response) );
//    }



}
