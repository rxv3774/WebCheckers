package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetSpectatorStopWatchingRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorStopWatchingRoute.class.getName());

    private static final String NAME_ATTR = "name";

    public GetSpectatorStopWatchingRoute() {
        LOG.config("GetSpectatorStopWatchingRoute initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSpectatorStopWatchingRoute is invoked.");

        response.redirect(WebServer.HOME_URL);
        halt();

        return null;
    }
}
