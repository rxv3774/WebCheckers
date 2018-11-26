package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final Gson gson;
    private final PlayerLobby playerLobby;
    private static final String SESSION_NAME_ATTR = "name";

    /**
     * Initializes post validate move Route
     *
     * @param gson
     */
    public PostValidateMoveRoute(Gson gson, PlayerLobby playerLobby) {

        this.gson = gson;
        this.playerLobby = playerLobby;

        LOG.config("PostValidateMoveRoute is initialized.");
    }

    /**
     * this is to get around gson not being mockable
     *
     * @param json - json to turn into move
     * @return Move generate from json
     */
    protected Move moveFromJson(String json) {
        final Move move = gson.fromJson(json, Move.class);

        return move;
    }

    /**
     * @param request  the HTTP request
     * @param response the HTTP response}
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostValidateMoveRoute is invoked.");

        final Session session = request.session();
        String currentPlayerName = session.attribute(SESSION_NAME_ATTR);
        Player player = playerLobby.getPlayerObject(currentPlayerName);

        if (player != null) {
            Match game = player.getMatch();

            if (game == null) {
                return gson.toJson(Message.ERR_NO_OPPONENT);
//                return gson.toJson(Message.TRUE );
            }

            Move move = moveFromJson(request.body());
            Board board = game.getBoard();
//            boolean redPlayer = game.getRedPlayer().equals(player);
            boolean redPlayer = game.doPlayersMatch(game.getRedPlayer(), player);

            System.out.println("one");
            System.out.println(!game.hasPendingMoves());
            System.out.println("two");


            System.out.println(move.isValid(board, redPlayer));

            if (!game.hasPendingMoves() && move.isValid(board, redPlayer)) { //single move or single jump
                game.addPendingMove(move);
                return gson.toJson(Message.VALID_MOVE);
            } else if (move.isJumpMove() && !game.hasPendingDJMoves() && move.isValid(board, redPlayer)) { //second move for double jump
                game.addPendingMove(move);
                return gson.toJson(Message.VALID_MOVE);
            } else if (move.isSingleMove() && game.hasPendingMoves()) { //only one move allowed if it's not a double jump
                return gson.toJson(Message.ERR_DOUBLE_MOVE);
            } else
                return gson.toJson(Message.ERR_INVALID_MOVE);
        }

        return gson.toJson(Message.ERR_NOT_SIGNED_IN);
    }
}
