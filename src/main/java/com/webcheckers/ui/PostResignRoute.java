package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;

public class PostResignRoute implements Route {

    //
    // Attributes
    //
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final Gson gson;

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private static final String SESSION_NAME_ATTR = "name";


    public PostResignRoute(PlayerLobby playerLobby, GameCenter gameCenter, Gson gson){
        // validation
        Objects.requireNonNull( playerLobby, "playerLobby must not be null");
        Objects.requireNonNull( gameCenter, "gameCenter must not be null");
        Objects.requireNonNull( gson, "gson must not be null");
        //
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        this.gson = gson;
        //
        LOG.config("PostResignRoute is initialized.");
    }


    /**
     * @param request  the HTTP request
     * @param response the HTTP response}
     * @return the rendered HTML for the Home page
     */
    @Override
    public String handle(Request request, Response response) {
        LOG.finer("PostResignRoute is invoked.");

        final Session session = request.session();
        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        Player player = playerLobby.getPlayerObject( currentPlayerName );

        gameCenter.endGame( player.getMatch() );

        return gson.toJson( Message.PLAYER_RESIGNATION );
    }


}