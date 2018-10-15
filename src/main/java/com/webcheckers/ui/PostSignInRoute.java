package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;



public class PostSignInRoute implements Route {

    //
    // Attributes
    //
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    private final static String PLAYER_NAME = "playerName";

    public static final String HOME_URL = "/";
    public static final String SIGNIN_URL = "/signIn";
    public static final String POST_NAME = "/postName";


    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );



    /**
     * The constructor for the {@code POST /signin} route handler.
     *
     * @param playerLobby
     *    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine
     *    template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    public PostSignInRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
        //
        LOG.config("PostSignInRoute is initialized.");
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String handle(Request request, Response response) {

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        String playerName = request.queryParams( PLAYER_NAME );

        ModelAndView mv = playerLobby.playerSignInProcess( playerName, response, request, vm );
        return templateEngine.render( mv );
    }
}