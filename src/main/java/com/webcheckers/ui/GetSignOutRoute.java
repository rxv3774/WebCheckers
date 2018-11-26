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

public class GetSignOutRoute implements Route {

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

    public GetSignOutRoute(PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.playerLobby = playerLobby;
        LOG.config("GetSignOutRoute is initialized.");
    }

    public String handle(Request request, Response response) {
        LOG.config("GetSignOutRoute is invoked.");

        final Session session = request.session();
        final Map<String, Object> vm = new HashMap<>();


        String currentPlayerName = session.attribute("name");
        Player currentPlayer = playerLobby.getPlayerObject( currentPlayerName );

        if(currentPlayer != null) {
            if(currentPlayer.isInGame()) {
                currentPlayer.endGame();
            }
            playerLobby.signOut(currentPlayer);
            session.removeAttribute("name");
        }
        response.redirect(WebServer.HOME_URL);
        return null;
    }

}
