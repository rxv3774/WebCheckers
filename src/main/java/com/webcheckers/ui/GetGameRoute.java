package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Match;
import com.webcheckers.appl.Player;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

import static spark.Spark.halt;

/*
 * UI controller to post the game
 *
 * Author: TeamD
 */
public class GetGameRoute implements Route{
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    static final String VIEW_NAME = "game.ftl";

    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );

    /**
     * The constructor for the {@code Get /game} route handler.
     *
     * @param playerLobby
     *    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine
     *    template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    public GetGameRoute(GameCenter gameCenter, PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * @param request  the HTTP request
     * @param response the HTTP response}
     * @return the rendered HTML for the Home page
     */
    @Override
    public String handle(Request request, Response response) {
        // start building the BoardView-Model
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();

        vm.put("title", "Game!");

        String currentPlayerName = session.attribute("name");
        Player player = playerLobby.getPlayerObject(currentPlayerName);

        if(player == null){
            response.redirect(WebServer.SIGNIN_URL);
            return null;
        }

        /*
         *Checks if current player isn't apart of a game with a board that's been initialized
         */
        if( !player.isInGame() ) {

            String opponentPlayerName = request.queryParams("name");
            Player opponent = playerLobby.getPlayerObject(opponentPlayerName);

            if (opponent == null) {
                response.redirect(WebServer.HOME_URL);
                halt();
            }
            if(opponent.isInGame()){
                response.redirect(WebServer.HOME_URL);
                halt();
            }
            gameCenter.createGame(player, opponent);
            response.redirect(WebServer.START_GAME);
            halt();
        }
        Match match = player.getMatch();

        vm.put("currentPlayer", player);
        vm.put("redPlayer", match.getRedPlayer());
        vm.put("whitePlayer", match.getWhitePlayer());
        vm.put("isWhitePlayer", match.getWhitePlayer() == player);
        vm.put("viewMode", player.getViewMode());
        vm.put("activeColor", match.getActiveColor());

        //Board
        vm.put("board", match.getBoard());


        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
