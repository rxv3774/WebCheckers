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
    private TemplateEngine engine;
    private Gson gson;

    /**
     * Setup before each test
     */
    @BeforeEach
    void setup() {
        engine = mock(TemplateEngine.class);
    }

    /**
     * Checks to ensure that the template engine is not null.
     */
    @Test
    public void test_constructor_templateEngine() {
        TemplateEngine templateEngine = null;
        gson = new Gson();

        assertThrows( NullPointerException.class, () -> {new WebServer(templateEngine, gson); });
    }

    /**
     * Checks to ensure that the gson is not null.
     */
    @Test
    public void test_constructor_gson() {
        gson = null;

        assertThrows( NullPointerException.class, () -> {new WebServer(engine, gson); });
    }
}
