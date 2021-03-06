package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.model.User;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final String NAME_ATTR = "name";

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

        String currentPlayerName = session.attribute(NAME_ATTR);
        User currentUser = playerLobby.getUserObject( currentPlayerName );

        if(currentUser != null) {
            if(currentUser.isInGame()) {
                gameCenter.endGame(currentUser.getMatch());
                currentUser.endGame();
            }
            playerLobby.signOut(currentUser);
            session.removeAttribute(NAME_ATTR);
        }
        response.redirect(WebServer.HOME_URL);
        return null;
    }

}
