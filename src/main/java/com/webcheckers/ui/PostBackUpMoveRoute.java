package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.Objects;
import java.util.logging.Logger;



public class PostBackUpMoveRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private Gson gson;

    public PostBackUpMoveRoute(Gson gson) {
        // validation
        Objects.requireNonNull(gson, "gson must not be null");
        //
        this.gson = gson;
        //
        LOG.config("PostBackUpMoveRoute initialized.");
    }

    /**
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