package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostSubmitTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final Gson gson;
    private final PlayerLobby playerLobby;

    /**
     * Create the Spark Route (ui controller) for the
     * {@code GET /} HTTP request.
     */
    public PostSubmitTurnRoute(Gson gson, PlayerLobby playerLobby) {
        LOG.config("PostSubmitTurnRoute is initialized.");

        this.gson = gson;
        this.playerLobby = playerLobby;
    }

    /**
     * Requests the submitted turn route.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSubmitTurnRoute is invoked.");

        final Session session = request.session();
        String currentPlayerName = session.attribute("name");
        Player player = playerLobby.getPlayerObject(currentPlayerName);

        if (player != null) {
            Match game = player.getMatch();
            game.doPendingMoves();
            game.changeActivePlayer(); //end turn

            return gson.toJson(Message.MOVE_SUBMITTED);
        }

        return gson.toJson(Message.ERR_NOT_SIGNED_IN);
    }
}
