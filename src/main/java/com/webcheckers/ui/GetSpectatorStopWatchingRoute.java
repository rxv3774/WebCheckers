package com.webcheckers.ui;

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

    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    public GetSpectatorStopWatchingRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        Objects.requireNonNull(playerLobby);
        Objects.requireNonNull(templateEngine);

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;

        LOG.config("GetSpectatorStopWatchingRoute initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSpectatorStopWatchingRoute is invoked.");

        final Session session = request.session();

        String spectatorName = session.attribute(NAME_ATTR);
        Spectator spectator = (Spectator) playerLobby.getUserObject(spectatorName);

        if (spectator.getMatch() == null) {
            response.redirect(WebServer.HOME_URL);
            halt();
        }

        return null;
    }
}
