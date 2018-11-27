package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/*
 * UI controller to post the game
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    private static final String VIEW_NAME = "game.ftl";
    private static final String ERROR = "error";

    /**
     * The constructor for the {@code Get /game} route handler.
     *
     * @param playerLobby the {@code playerLobby}
     * @param templateEngine template engine to use for rendering HTML page
     * @throws NullPointerException when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    public GetGameRoute(GameCenter gameCenter, PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * @param request  the HTTP request
     * @param response the HTTP response}
     * @return the rendered HTML for the Home page
     */
    @Override
    public String handle(Request request, Response response) {
        // start building the BoardView-Model
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();

        vm.put("title", "Game!");

        String currentUserName = session.attribute("name");
        User user = playerLobby.getUserObject(currentUserName);

        if (user == null) {
            response.redirect(WebServer.SIGN_IN_URL);
            halt();
            return null;
        }

        /*
         *Checks if current player isn't apart of a game with a board that's been initialized
         */
        if (!user.isInGame()) {

            String opponentPlayerName = request.queryParams("name");
            User opponent = playerLobby.getUserObject(opponentPlayerName);

            if (opponent == null) {
                request.session().attribute("errorMessage", "Player doesn't exist.");
                response.redirect(WebServer.HOME_URL);
                halt();
            }
            if (opponent.equals(user)) {
                request.session().attribute("errorMessage", "Must play with player other than yourself.");
                request.session().attribute("messageType", ERROR);
                response.redirect(WebServer.HOME_URL);
                halt();
            }
            if (opponent.isInGame()) {
                playerLobby.removeUser(user);
                user = new Spectator(user.getName());
                playerLobby.addUser(user);
                opponent.getMatch().joinSpectator((Spectator) user);
                System.out.println(user.getViewMode());
                response.redirect(WebServer.SPECTATOR_GAME);
                // TODO: Handle possible error in joining spectator
//                request.session().attribute("errorMessage", "Player is already in a game.");
//                response.redirect(WebServer.HOME_URL);
                halt();
            }
            gameCenter.createGame((Player) user, (Player) opponent);
            response.redirect(WebServer.START_GAME);
            halt();
        }
        Match match = user.getMatch();

        vm.put("currentPlayer", user);
        vm.put("redPlayer", match.getRedPlayer());
        vm.put("whitePlayer", match.getWhitePlayer());
        vm.put("isWhitePlayer", match.getWhitePlayer() == user);
        vm.put("viewMode", user.getViewMode());
        vm.put("activeColor", match.getActiveColor());

        //Board
        vm.put("board", match.getBoard());

        if (!match.canPlay()) {
            match.declareWinner();
        }
        if (match.hasWinner()) {
            if (match.isWinner((Player) user)) {
                vm.put("message", Message.WINNER);
            } else {
                gameCenter.endGame(match);
                vm.put("message", Message.LOSER);

            }
        } else if (!match.isRunning() && gameCenter.containsMatch(match)) {
            gameCenter.endGame(match);
            vm.put("message", Message.RESIGNED);
        }

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
