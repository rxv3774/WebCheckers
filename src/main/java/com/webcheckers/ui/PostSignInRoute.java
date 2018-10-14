package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.Route;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;

import spark.Request;
import spark.Response;
import spark.TemplateEngine;
import static spark.Spark.halt;



public class PostSignInRoute implements Route {

    //
    // Attributes
    //
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    private final static String PLAYER_NAME = "playerName";


    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );



    /**
     * The constructor for the {@code POST /signin} route handler.
     *
     * @param playerLobby
     *    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine
     *    template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    public PostSignInRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
        //
        LOG.config("PostSignInRoute is initialized.");
    }






    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException
     *    when an invalid result is returned after making a guess
     */
    @Override
    public String handle(Request request, Response response) {

//        final Session session = request.session();
//        session.attribute("playerName", playerLobby);

        String playerName = request.queryParams( PLAYER_NAME );

        playerLobby.playerSignInProcess( playerName, request );

        String tmp = request.attribute( "playerName" );
        System.out.println( tmp );



        // start building the View-Model
//        final Map<String, Object> vm = new HashMap<>();

//        vm.put("title", "Welcome!");
//        vm.put( GetHomeRoute.GAMEHOME_TITLE , "testName" );
//        vm.put(GetHomeRoute.NEW_PLAYER_ATTR, Boolean.FALSE);

//        ModelAndView mv;

//        if( playerLobby.constainsPlayer( ) ){

//        }





//        return templateEngine.render( mv );
        return "STUB";
    }


//    private ModelAndView error(){}


}