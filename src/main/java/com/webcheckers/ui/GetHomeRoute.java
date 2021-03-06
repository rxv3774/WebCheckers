package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.User;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final String TITLE_ATTR = "title";
    private static final String MESSAGE_TYPE_ATTR = "messageType";
    private static final String PLAYER_LOBBY_ATTR = "playerLobby";
    private static final String NAME_ATTR = "name";
    private static final String ERROR_ATTR = "errorMessage";

//    private static final String VIEW_NAME = "home.ftl";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetHomeRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetHomeRoute is invoked.");

        final Session session = request.session();

        Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, "Welcome!");
        vm.put(MESSAGE_TYPE_ATTR, "info");

        if (playerLobby.getLobbySize() > 0) {

            String currentPlayer = session.attribute(NAME_ATTR);

            if (currentPlayer != null) {
                vm.put(WebServer.SIGNED_IN, "The current signed in user is: " + currentPlayer);
                vm.put(WebServer.PLAYER_LIST, playerLobby.getUserNamesAsString(currentPlayer));
            } else {
                vm.put(WebServer.PLAYER_LIST, "The number of players signed in is: " + playerLobby.getLobbySize());
            }

            String errorMessage = session.attribute(ERROR_ATTR);

            if (errorMessage != null) vm.put(WebServer.ERROR_MESSAGE, "ERROR: " + errorMessage);
        }

        if (playerLobby.getLobbySize() > 1) {
            vm.put(WebServer.SHOW_BUTTON, true);

            String currentPlayer = session.attribute(NAME_ATTR);
            User user = playerLobby.getUserObject(currentPlayer);

            if (user instanceof Player && gameCenter.containsPlayer((Player) user)) {
                response.redirect(WebServer.START_GAME);
                halt();
            }
        } else {
            vm.put(WebServer.SHOW_BUTTON, false);
        }

        if (playerLobby.getLobbySize() == 0) {
            vm.put(WebServer.PLAYER_LIST, "There aren't any players signed in");
        }

        return templateEngine.render(new ModelAndView(vm, WebServer.HOME_FILE));
    }
}