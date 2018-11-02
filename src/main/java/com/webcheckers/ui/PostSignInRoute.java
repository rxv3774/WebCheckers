package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


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


    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());


    /**
     * The constructor for the {@code POST /signin} route handler.
     *
     * @param playerLobby    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine template engine to use for rendering HTML page
     * @throws NullPointerException when the {@code gameCenter} or {@code templateEngine} parameter is null
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
     * @param request  the HTTP request
     * @param response the HTTP response}
     * @return the rendered HTML for the Home page
     */
    @Override
    public String handle(Request request, Response response) {
        final Session session = request.session();

        // start building the BoardView-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        String playerName = request.queryParams(PLAYER_NAME);

        session.attribute("name", playerName); //add playerName to session

        ModelAndView mv = playerLobby.playerSignInProcess(playerName, response, vm);

        if (mv == null) {
            return "";
        }

        return templateEngine.render(mv);
    }
}