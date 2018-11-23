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

import java.util.Objects;
import java.util.logging.Logger;


//private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );



public class PostBackUpMoveRoute implements Route {


    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private Gson gson;

    public PostBackUpMoveRoute(Gson gson) {
        // validation
        Objects.requireNonNull(gson, "gson must not be null");
//        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.gson = gson;
//        this.playerLobby = playerLobby;
        //
        LOG.config("PostCheckTurnRoute initialized.");

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
    public String handle( Request request, Response response ) {
        return gson.toJson( Message.BACKUPMOVE );
    }

}
