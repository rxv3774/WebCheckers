package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;
import static spark.Spark.halt;



public class PostSignInRoute implements Route {

    //
    // Attributes
    //
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    static final String INPUT_PARAM = "playerName";
    static final String SIGNIN_PARAM = "signedin";
    static final String MESSAGE_TYPE_ATTR = "messageType";
    static final String MESSAGE_TYPE = "Current Player:";



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

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }






    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException
     *    when an invalid result is returned after making a guess
     */
    @Override
    public String handle(Request request, Response response) {

        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();

        vm.put("title", "Welcome!");

//        vm.put(GetHomeRoute.TITLE_ATTR, GetGameRoute.TITLE);
//        vm.put(GetHomeRoute.NEW_PLAYER_ATTR, Boolean.FALSE);

        // retrieve request parameter
        final String guessStr = request.queryParams(INPUT_PARAM);

        //Add player to playerLobby
        if(playerLobby.isValidName(guessStr)) {
            playerLobby.addPlayer(new Player(guessStr));
            vm.put(SIGNIN_PARAM, guessStr);
            vm.put(MESSAGE_TYPE_ATTR, MESSAGE_TYPE);
        }

        ModelAndView mv = new ModelAndView(vm, GetHomeRoute.VIEW_NAME);

        System.out.println( "this is a test");

        return templateEngine.render(mv);
    }



}