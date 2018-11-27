package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.AI;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import com.webcheckers.model.User;
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
        User user = playerLobby.getUserObject(currentPlayerName);

        if (user != null) {
            Match game = user.getMatch();
            if(game.hasPendingMoves()) {
                if(!game.doubleJumpAvailable()) {

                    game.doPendingMoves();
                    game.changeActivePlayer(); //end turn

                    if(game.canPlay() && game.getWhitePlayer().isAI()){
                        //Select AI move
                        Move pendingMove = AI.getAIMove(game);
                        game.addPendingMove(pendingMove);

                        //checking for double jump move
                        if(game.doubleJumpAvailable()){
                            Move secondMove = game.chooseAISecondJump();
                            game.addPendingMove(secondMove);
                        }
                        game.doPendingMoves();
                        game.changeActivePlayer();
                    }

                    return gson.toJson(Message.MOVE_SUBMITTED);
                } else
                    return gson.toJson(Message.ERR_DJ_AVAILABLE);
            } else
                return gson.toJson(Message.ERR_NO_PENDING_MOVES);
        }

        return gson.toJson(Message.ERR_NOT_SIGNED_IN);
    }
}
