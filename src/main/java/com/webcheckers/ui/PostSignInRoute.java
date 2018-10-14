package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;



public class PostSignInRoute implements Route {

    //
    // Attributes
    //
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;



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

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }






    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException
     *    when an invalid result is returned after making a guess
     */
    @Override
    public String handle(Request request, Response response) {

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();

//        vm.put(GetHomeRoute.TITLE_ATTR, GetGameRoute.TITLE);
//        vm.put(GetHomeRoute.NEW_PLAYER_ATTR, Boolean.FALSE);

        System.out.println( "this is a test");

        return "STUB";
    }



}