package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;


public class PostStartGame implements Route{
    private final TemplateEngine templateEngine;

    static final String VIEW_NAME = "game.ftl";

    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );

    /**
     * The constructor for the {@code POST /chooseName} route handler.
     *
     * @param playerLobby
     *    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine
     *    template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    public PostStartGame(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        //
        LOG.config("PostStartGame is initialized.");
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String handle(Request request, Response response) {
        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();
        final PlayerLobby playerLobby = session.attribute(WebServer.PLAYER_LOBBY);
        final GameCenter gameCenter = session.attribute(WebServer.GAME_CENTER);

        String currentPlayerName = session.attribute("name");
        Player currentPlayerObject = playerLobby.getPlayerObject(currentPlayerName);

        String opponentPlayerName = request.queryParams("name");
        Player opponentPlayerObject = playerLobby.getPlayerObject(opponentPlayerName);

        vm.put("title", "Game!");
        vm.put("currentPlayer", currentPlayerObject);
        vm.put("redPlayer", currentPlayerObject);
        vm.put("whitePlayer", opponentPlayerObject);

        vm.put("viewMode", "PLAY");
        vm.put("activeColor", "RED");

        if (gameCenter.containsPlayer(opponentPlayerObject)) {
            response.redirect(WebServer.HOME_URL);
            halt();
        } else {
            gameCenter.addMatch(currentPlayerObject, opponentPlayerObject);
        }

        vm.put("board", new BoardView());

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
