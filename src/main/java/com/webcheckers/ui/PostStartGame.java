package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;


public class PostStartGame implements Route{

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );

    /**
     * The constructor for the {@code POST /chooseName} route handler.
     *
     * @param playerLobby
     *    {@Link playerLobby} CHANGE ME!!!
     * @param templateEngine
     *    template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code gameCenter} or {@code templateEngine} parameter is null
     */
    public PostStartGame(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
        //
        LOG.config("PostStartGame is initialized.");
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String handle(Request request, Response response) {
        return null;
    }
}