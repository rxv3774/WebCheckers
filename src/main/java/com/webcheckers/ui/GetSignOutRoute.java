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

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    public GetSignOutRoute(PlayerLobby playerLobby, GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;

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
                gameCenter.endGame(currentPlayer.getMatch());
                currentPlayer.endGame();
            }
            playerLobby.signOut(currentPlayer);
            session.removeAttribute("name");
        }
        response.redirect(WebServer.HOME_URL);
        return null;
    }

}
