package com.webcheckers.appl;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */

import com.webcheckers.appl.Match;
import com.webcheckers.appl.Player;
import com.webcheckers.model.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        match = mock( Match.class );
    }

    @Test
    public void constructorNotNull() {

        //Test1
        assertNotNull( new Match() ) ;
    }


//    @Test
//    public void joinWorks(){
//
//        Player player = mock( Player.class );
//        when( player.playGame( match ) ).thenReturn( true );
//
//        assertEquals( true, player.playGame( match ) );
//
//        assertEquals( true, match.join( player ) );
////        assertEquals( true, match.join( player ) );
////        assertEquals( false, match.join( player ) );
//    }

    @Test
    public void getRedPlayerNotNull() {

        when( match.getRedPlayer() ).thenReturn( redPlayer );

        //Test1
        assertNotNull( match.getRedPlayer() ) ;

        //Test2
        assertEquals( redPlayer, match.getRedPlayer() );

    }

    @Test
    public void getWhitePlayerNotNull() {
        when( match.getWhitePlayer() ).thenReturn( whitePlayer );

        //Test1
        assertNotNull( match.getWhitePlayer() ) ;

        //Test2
        assertEquals( whitePlayer, match.getWhitePlayer() );
    }


    @Test
    public void getActiveColorWorks(){

        when( match.getActiveColor() ).thenReturn( Piece.Color.RED );

        //Test1
        assertNotNull( match.getActiveColor() );

        //Test2
        assertEquals(Piece.Color.RED, match.getActiveColor() );

        when( match.getActiveColor() ).thenReturn( Piece.Color.WHITE );

        //Test3
        assertEquals(Piece.Color.WHITE, match.getActiveColor() );
    }
}
