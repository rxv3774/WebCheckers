package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;


public class PostSignInRoute implements Route {
    //
    // Attributes
    //
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final String SESSION_NAME_ATTR = "name";
    private static final String PLAYER_NAMES_ATTR = "playerNames";
    private final static String PLAYER_NAME_ATTR = "playerName";
    private static final String TITLE_ATTR = "title";
    private static final String MESSAGE_TYPE_ATTR = "messageType";
    private static final String ERROR_MESSAGE_ATTR = "showErrorMessage";

    private static final String TITLE = "Sign-In";
    private static final String ERROR = "error";
    private static final String VIEW_NAME = "signin.ftl";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;


    /**
     * The constructor for the {@code POST /signin} route handler.
     *
     * @param playerLobby    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine template engine to use for rendering HTML page
     * @throws NullPointerException when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    PostSignInRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
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
        final Map<String, Object> vm = new HashMap<>();
        final String playerName = request.queryParams(PLAYER_NAME_ATTR);

        vm.put(TITLE_ATTR, TITLE);

        // Check name validity
        if (!playerLobby.isValidName(playerName)) {
            vm.put(MESSAGE_TYPE_ATTR, ERROR);
            vm.put(ERROR_MESSAGE_ATTR, "The name you entered contains illegal characters.");
            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }

        // Checks if name is in use
        if (!playerLobby.playerNameInUse(playerName)) {
            Player newPlayer = new Player(playerName);

            session.attribute(PLAYER_NAMES_ATTR, playerLobby.getPlayersNamesAsArrayList());
            session.attribute(SESSION_NAME_ATTR, playerName);

            playerLobby.addPlayer(newPlayer);

            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        } else {
            vm.put(MESSAGE_TYPE_ATTR, ERROR);
            vm.put(ERROR_MESSAGE_ATTR, "That name is already in use");
            return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
        }
    }
}