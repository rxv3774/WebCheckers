package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Spectator;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;

public class PostSpectatorCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorCheckTurnRoute.class.getName());

    private static final String SESSION_NAME_ATTR = "name";
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    private Gson gson;
    private PlayerLobby playerLobby;

    private Piece.Color previousActiveColor = Piece.Color.RED;

    public PostSpectatorCheckTurnRoute(Gson gson, PlayerLobby playerLobby) {
        Objects.requireNonNull(gson);
        Objects.requireNonNull(playerLobby);

        this.gson = gson;
        this.playerLobby = playerLobby;

        LOG.config("PostSpectatorCheckTurnRoute initialized");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.fine("PostSpectatorCheckTurnRoute is invoked");

        final Session session = request.session();
        String currentSpectatorName = session.attribute(SESSION_NAME_ATTR);

        Spectator spectator = (Spectator) playerLobby.getUserObject(currentSpectatorName);

        if (spectator != null) {
//            return gson.toJson(new Message(FALSE, Message.Type.info));
            Match match = spectator.getMatch();
            if (match != null) {
                if (match.getActiveColor() == previousActiveColor) {
                    previousActiveColor = match.getActiveColor();
                    return gson.toJson(new Message(FALSE, Message.Type.info));
                } else {
                    previousActiveColor = match.getActiveColor();
                    return gson.toJson(new Message(TRUE, Message.Type.info));
                }
            } else {
                return gson.toJson(new Message(TRUE, Message.Type.info));
            }
        }

        return null;
    }
}
