package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Spectator;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetSpectatorStopWatchingRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorStopWatchingRoute.class.getName());

    private static final String NAME_ATTR = "name";

    public GetSpectatorStopWatchingRoute() {
        LOG.config("GetSpectatorStopWatchingRoute initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSpectatorStopWatchingRoute is invoked.");

        response.redirect(WebServer.HOME_URL);

        return null;
    }
}
