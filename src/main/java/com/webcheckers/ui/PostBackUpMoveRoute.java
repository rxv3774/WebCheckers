package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import com.webcheckers.model.User;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;



public class PostBackUpMoveRoute implements Route {

    private static final String SESSION_NAME_ATTR = "name";

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private Gson gson;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;

    public PostBackUpMoveRoute(Gson gson, GameCenter gameCenter, PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(gson, "gson must not be null");
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.gson = gson;
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        //
        LOG.config("PostBackUpMoveRoute initialized.");
    }

    /**
     *
     * @param request
     *  The http request
     * @param response
     *  The http response
     * @return
     *  The returned HTML page
     *
     */
    @Override
    public String handle( Request request, Response response ) {

        final Session session = request.session();
        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        User user = playerLobby.getUserObject(currentPlayerName);
        Match match = user.getMatch();

        match.removePendingMove();

        return gson.toJson( Message.BACKUPMOVE );
    }

}