package com.webcheckers.ui;

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
        User currentUser = playerLobby.getUserObject( currentPlayerName );

        if(currentUser != null) {
            if(currentUser.isInGame()) {
                currentUser.endGame();
            }
            playerLobby.signOut(currentUser);
            session.removeAttribute("name");
        }
        response.redirect(WebServer.HOME_URL);
        return null;
    }

}
