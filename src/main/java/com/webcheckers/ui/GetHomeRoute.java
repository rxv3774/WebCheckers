package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.redirect;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());


    public static final String GAME_HOME_TITLE = "title";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    static final String VIEW_NAME = "home.ftl";

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetHomeRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session session = request.session();
        session.attribute(WebServer.PLAYER_LOBBY, playerLobby);
        session.attribute(WebServer.GAME_CENTER, gameCenter);

        LOG.finer("GetHomeRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        vm.put( "messageType", "info");

        PlayerLobby playerLobby = session.attribute( "playerLobby"  );

        if( playerLobby.getLobbySize() > 0 ){

            String currentPlayer = session.attribute( "name" );

            if( currentPlayer != null ) {
                vm.put( WebServer.SIGNEDIN, "The current signed in user is: " + currentPlayer);
                vm.put( WebServer.PLAYERLST, playerLobby.getPlayerNameLst( currentPlayer ) );
            }
            else {
                vm.put( WebServer.PLAYERLST, "The number of players signed in is: " + playerLobby.getLobbySize());
            }
        }

        if( playerLobby.getLobbySize() > 1){
            vm.put( WebServer.SHOW_BUTTON, true);

            GameCenter gameCenter = session.attribute( WebServer.GAME_CENTER );

            String currentPlayer = session.attribute( "name" );
            Player player = playerLobby.getPlayerObject( currentPlayer );

            if( gameCenter.containsPlayer( player ) ){
                System.out.println("REDIRECT");



                response.redirect(  WebServer.START_GAME );
//                response.redirect(  WebServer.SIGNIN_URL );
                halt();
            }
            else{
                System.out.println("NO REDIRECT");
            }

        }
        else{
            vm.put( WebServer.SHOW_BUTTON, false);
        }

        if( playerLobby.getLobbySize() == 0){
            vm.put( WebServer.PLAYERLST, "There aren't any players signed in");
        }












        return templateEngine.render(new ModelAndView(vm, WebServer.HOME_FILE ) );
    }
}