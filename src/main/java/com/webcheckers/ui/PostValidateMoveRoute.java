package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final Gson gson;
    private final PlayerLobby playerLobby;

    /**
     * Initializes post validate move Route
     * @param gson
     */
    public PostValidateMoveRoute(Gson gson, PlayerLobby playerLobby){

        this.gson = gson;
        this.playerLobby = playerLobby;

        LOG.config("PostValidateMoveRoute is initialized.");
    }

    /**
     * this is to get around gson not being mockable
     * @param json - json to turn into move
     * @return Move generate from json
     */
    protected Move moveFromJson(String json){
        return gson.fromJson(json ,Move.class);
    }

    /**
     * @param request  the HTTP request
     * @param response the HTTP response}
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("PostValidateMoveRoute is invoked.");

        final Session session = request.session();
        String currentPlayerName = session.attribute("name");
        Player player = playerLobby.getPlayerObject(currentPlayerName);

        if(player != null){
            Match game = player.getMatch();

            if(game == null){
                return gson.toJson(Message.ERR_NO_OPPONENT);
            }

            Move move = moveFromJson(request.body());

            game.addPendingMove(move);
            return gson.toJson(Message.VALID_MOVE);
        }

        return gson.toJson(Message.ERR_NOT_SIGNED_IN);
    }
}
