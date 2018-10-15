package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());


    public static final String GAMEHOME_TITLE = "title";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    static final String VIEW_NAME = "home.ftl";

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetHomeRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
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
        session.attribute("playerLobby", playerLobby);

        LOG.finer("GetHomeRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        Session httpSession = request.session();
        System.out.println( httpSession.attributes() );
        String tmp = httpSession.attribute( "playerLobby" );
        System.out.println( tmp );

//        vm.put( "signedin", "")

        if( playerLobby.getLobbySize() > 0) {
//            vm.put("currentPlayer", );

            String playerName = request.queryParams( "playerName" );
            System.out.println( playerName );

        }
//        else{
//
//        }


        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

}