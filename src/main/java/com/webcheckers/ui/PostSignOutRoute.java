package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final String SESSION_NAME_ATTR = "name";
    private static final String PLAYER_NAMES_ATTR = "playerNames";
    private final static String PLAYER_NAME_ATTR = "playerName";
    private static final String TITLE_ATTR = "title";
    private static final String MESSAGE_TYPE_ATTR = "messageType";
    private static final String ERROR_MESSAGE_ATTR = "showErrorMessage";

    private static final String TITLE = "/signout";
    private static final String ERROR = "error";
    private static final String VIEW_NAME = "game.ftl";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public PostSignOutRoute(GameCenter gameCenter, PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        //
        LOG.config("PostSignOutRoute is initialized.");
    }

    public String handle(Request request, Response response) {


        System.out.println("BBBBB");

        return "bbbbb";

//        final Session session = request.session();
//        final Map<String, Object> vm = new HashMap<>();
//
//
//        final String currentPlayerName = request.queryParams( SESSION_NAME_ATTR );
//        Player currentPlayer = playerLobby.getPlayerObject( currentPlayerName );
//        Match currentMatch = currentPlayer.getMatch();
//
//
//        Player opponent = currentMatch.getOpponent(currentPlayer);
//
//        vm.put(TITLE_ATTR, TITLE);
//
//
//        System.out.println( currentPlayerName );
//        System.out.println( opponent.getName() );
//
//
//
//
//        if (currentPlayer == null || opponent == null) {
//            vm.put(MESSAGE_TYPE_ATTR, ERROR);
//            vm.put(ERROR_MESSAGE_ATTR, "Player does not exist");
//        }
//
//        if (currentPlayer.isInGame() && opponent.isInGame()  ) {
//
//            gameCenter.endGame(currentPlayer.getMatch());
//            gameCenter.endGame(opponent.getMatch());
//
//            response.redirect(WebServer.HOME_URL);
//            halt();
//        }

//        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }

}
