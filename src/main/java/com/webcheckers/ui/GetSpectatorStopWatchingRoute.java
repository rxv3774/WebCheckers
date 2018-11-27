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

    private PlayerLobby playerLobby;
    private GameCenter gameCenter;

    public GetSpectatorStopWatchingRoute(PlayerLobby playerLobby, GameCenter gameCenter) {
        Objects.requireNonNull(playerLobby);
        Objects.requireNonNull(gameCenter);

        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;

        LOG.config("GetSpectatorStopWatchingRoute initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSpectatorStopWatchingRoute is invoked.");

        final Session session = request.session();

        String spectatorName = session.attribute(NAME_ATTR);
        Spectator spectator = (Spectator) playerLobby.getUserObject(spectatorName);

        response.redirect(WebServer.HOME_URL);

        return null;
    }
}
