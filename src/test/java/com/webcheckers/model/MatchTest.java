package com.webcheckers.model;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */

import com.webcheckers.appl.Match;
import com.webcheckers.appl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class MatchTest {

    private Player redPlayer;
    private Player whitePlayer;
    private TemplateEngine engine;
    private Match match;


    @BeforeEach
    void setup() {
        engine = mock(TemplateEngine.class);
        redPlayer = mock( Player.class );
        whitePlayer = mock( Player.class );

        match = new Match( redPlayer, whitePlayer);
    }


    @Test
    public void constructorNotNull() {
        assertNotNull( new Match( redPlayer, whitePlayer) ) ;
    }

    @Test
    public void getRedPlayerNotNull() {
        assertNotNull( match.getRedPlayer() ) ;
    }

    @Test
    public void getWhitePlayerNotNull() {
        assertNotNull( match.getWhitePlayer() ) ;
    }
}
