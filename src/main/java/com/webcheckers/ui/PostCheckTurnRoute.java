package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private boolean moveMade;

    private final PlayerLobby playerLobby;

    /**
     * Initializes the PostCheckTurnRoute
     */
    public PostCheckTurnRoute(PlayerLobby playerLobby){
        LOG.config("PostCheckTurnRoute initialized.");

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
        LOG.finer("PostResignGameRoute is invoked");

        final Session session = request.session();
        String currentPlayerName = session.attribute("name");
        Player player = playerLobby.getPlayerObject(currentPlayerName);

        if(player != null){
            Match game = player.getMatch();
            if(game != null) {
                if (game.getActivePlayer() == player) {
                    return Message.TRUE;
                }
                if(game.hasWinner()){
                    moveMade = true;
                    return Message.TRUE;
                }
                return Message.FALSE;
            }
        }
        return null;
    }
}
