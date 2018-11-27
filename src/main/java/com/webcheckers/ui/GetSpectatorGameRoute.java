package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Spectator;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetSpectatorGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private static final String VIEW_NAME = "game.ftl";

    private static final String TITLE_ATTR = "title";
    private static final String NAME_ATTR = "name";
    private static final String CURRENT_PLAYER_ATTR = "currentPlayer";
    private static final String RED_PLAYER_ATTR = "redPlayer";
    private static final String WHITE_PLAYER_ATTR = "whitePlayer";
    private static final String IS_WHITE_PLAYER_ATTR = "isWhitePlayer";
    private static final String VIEW_MODE_ATTR = "viewMode";
    private static final String ACTIVE_COLOR_ATTR = "activeColor";
    private static final String BOARD_ATTR = "board";
    private static final String MESSAGE_ATTR = "message";

    private static final String TITLE = "Game!";

    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    public GetSpectatorGameRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        Objects.requireNonNull(playerLobby);
        Objects.requireNonNull(templateEngine);

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;

        LOG.config("GetSpectatorGameRoute initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();

        vm.put(TITLE_ATTR, TITLE);

        String currentSpectatorName = session.attribute(NAME_ATTR);
        Spectator spectator = (Spectator) playerLobby.getUserObject(currentSpectatorName);

        if (spectator == null || !spectator.isInGame()) {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

        Match match = spectator.getMatch();

        vm.put(CURRENT_PLAYER_ATTR, match.getActivePlayer());
        vm.put(RED_PLAYER_ATTR, match.getRedPlayer());
        vm.put(WHITE_PLAYER_ATTR, match.getWhitePlayer());
        vm.put(IS_WHITE_PLAYER_ATTR, false);
        vm.put(VIEW_MODE_ATTR, spectator.getViewMode());
        vm.put(ACTIVE_COLOR_ATTR, match.getActiveColor());
        vm.put(BOARD_ATTR, match.getBoard());

        // TODO: Inform spectators of win/loss/resignation

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
