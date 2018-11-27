package com.webcheckers.ui;

import com.google.gson.Gson;
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

import static spark.Spark.halt;

public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private static final String SESSION_NAME_ATTR = "name";

    private final Gson gson;
    private final PlayerLobby playerLobby;

    /**
     * Initializes the PostCheckTurnRoute
     */

    public PostCheckTurnRoute(Gson gson, PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(gson, "gson must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.gson = gson;
        this.playerLobby = playerLobby;
        //
        LOG.config("PostCheckTurnRoute initialized.");
    }

    /**
     * Requests the post check turn
     *
     * @param request  The http request
     * @param response The http response
     * @return The returned HTML page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostResignGameRoute is invoked.");

        final Session session = request.session();
        String currentPlayerName = session.attribute(SESSION_NAME_ATTR);
        Player player = (Player) playerLobby.getUserObject(currentPlayerName);

        if (player != null) {
            Match game = player.getMatch();
            if (game != null) {
                if (game.getActivePlayer() == player) {
                    return gson.toJson(Message.TRUE);
                }
                if (game.hasWinner()) {
                    return gson.toJson(Message.TRUE);
                }
                return gson.toJson(Message.FALSE);
            } else {
                // Since the other player Quit, player wins.
                player.increaseGamesWon();

                // This tells the client to refresh the page.
                return gson.toJson(Message.OPPONENT_RESIGN);
            }
        }
        return null;
    }
}
