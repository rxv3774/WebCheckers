package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final Gson gson;

    /**
     * Initializes post validate move Route
     *
     * @param gson
     */
    public PostValidateMoveRoute(Gson gson) {

        this.gson = gson;

        LOG.config("PostValidateMoveRoute is initialized.");
    }

    /**
     * this is to get around gson not being mockable
     *
     * @param json - json to turn into move
     * @return Move generate from json
     */
    protected Move moveFromJson(String json) {
        return gson.fromJson(json, Move.class);
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostValidateMoveRoute is invoked.");

        final Session session = request.session();
        Player player = session.attribute("name");

        if (player != null) {
            Match match = player.getMatch();

            if (match == null) {
                return Message.ERR_NO_OPPONENT;
            }

            Move move = moveFromJson(request.body());
            move.connectToBoard(match.getBoard());

            if (match.isJumpAvailible()) {
                if (!move.isJump()) {
                    return Message.ERR_INVALID_MOVE;
                }
            }
            if (match.hasPendingMove()) {
                if (match.validateMoveOnChain(move)) {
                    match.addPendingMove(move);
                    return Message.VALID_MOVE;
                }
                return Message.ERR_INVALID_MOVE;
            } else if (move.isValid()) {
                match.addPendingMove(move);
                return Message.VALID_MOVE;
            } else {
                return Message.ERR_INVALID_MOVE;
            }
        }
        response.redirect(WebServer.SIGNIN_URL);
        return Message.ERR_NOT_SIGNED_IN;

    }
}
