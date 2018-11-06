package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class WebServerTest {

    //Attributes
    private final String TEMPLATE_ENGINE_NULL_ERROR = "templateEngine must not be null";
    private final String GSON_NULL_ERROR = "gson must not be null";
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private TemplateEngine engine;
    private Gson gson;

    /**
     * Setup before each test
     */
    @BeforeEach
    void setup() {
        engine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();
        gson = new Gson();
    }

    @Test
    void test_validConstructor() {
        new WebServer(engine, gson);
    }

    /**
     * Checks to ensure that the template engine is not null.
     */
    @Test
    void test_invalidConstructorEngine() {
        assertThrows(NullPointerException.class, () -> {
            new WebServer(null, gson);
        });
    }

    /**
     * Checks to ensure that the gson is not null.
     */
    @Test
    void test_invalidConstructorGson() {
        assertThrows(NullPointerException.class, () -> {
            new WebServer(engine, null);
        });
    }
}
