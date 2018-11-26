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

import static spark.Spark.halt;

public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private boolean moveMade;

    private final Gson gson;
    private final PlayerLobby playerLobby;

    private static final String SESSION_NAME_ATTR = "name";

    /**
     * Initializes the PostCheckTurnRoute
     */
    public PostCheckTurnRoute(Gson gson, PlayerLobby playerLobby){
        LOG.config("PostCheckTurnRoute initialized.");

        this.gson = gson;
        this.playerLobby = playerLobby;
    }

    /**
     *
     * Requests the post check turn
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
    public Object handle(Request request, Response response) {
        final Session session = request.session();
        String currentPlayerName = session.attribute( SESSION_NAME_ATTR );
        Player player = playerLobby.getPlayerObject(currentPlayerName);

        if(player != null){

            if( player.getMatch() != null) {

                Match game = player.getMatch();
                if (game != null) {
                    if (game.getActivePlayer() == player) {
                        return gson.toJson(Message.TRUE);
                    }
                    if (game.hasWinner()) {
                        moveMade = true;
                        return gson.toJson(Message.TRUE);
                    }
                    return gson.toJson(Message.FALSE);
                }
            }
            else {
                // Since the other player Quit, player wins.
                player.increaseGamesWon();

                // This tells the client to refresh the page.
                return gson.toJson( Message.OPPONENT_RESIGN );
            }
        }
        return null;
    }
}
