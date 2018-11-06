package com.webcheckers.appl;

import com.webcheckers.model.Match;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Application-tier")
class GameCenterTest {


    @Test
    public void createGameWorks(){

        Player p1 = new Player( "Batman" );
        Player p2 = new Player( "Darth Vader" );

        GameCenter gameCenter = new GameCenter();

        //Test1 doesn't return a null value
        assertNotNull( gameCenter.createGame( p1, p2 ) );
    }


    @Test
    public void containsPlayerWorks(){
        Player p1 = new Player( "Batman" );
        Player p2 = new Player( "Darth Vader" );
        Player p3 = new Player( "DeadPool" );

        GameCenter gameCenter = new GameCenter();

        gameCenter.createGame( p1, p2 );

        //Test1 doesn't return a null value
        assertNotNull( gameCenter.containsPlayer( p1 ) );

        //Test2 player is in a match so it's true
        assertTrue( gameCenter.containsPlayer( p1 ) );

        //Test3 player is in a match so it's true
        assertTrue( gameCenter.containsPlayer( p2 ) );

        //Test4 player isn't in a match so it's false
        assertFalse( gameCenter.containsPlayer( p3 ) );
    }


    @Test
    public void endGameWorks(){
        Player p1 = new Player( "Batman" );
        Player p2 = new Player( "Darth Vader" );

        GameCenter gameCenter = new GameCenter();

        Match match = gameCenter.createGame( p1, p2 );

        //Test1 this proves that match exists and is running
        assertTrue( gameCenter.runningMatches( match ) );

        gameCenter.endGame( match );

        //Test2 this proves that match doesn't exist and isn't running
        assertFalse( gameCenter.runningMatches( match ) );
    }


    @Test
    public void runningMatchesWorks() {
        Player p1 = new Player("Batman");
        Player p2 = new Player("Brian");

        GameCenter gameCenter = new GameCenter();

        Match match = gameCenter.createGame(p1, p2);

        //Test1 this proves that match exists and is running
        assertTrue(gameCenter.runningMatches(match) );

        gameCenter.endGame(match);

        //Test2 this proves that match doesn't exist and isn't running
        assertFalse(gameCenter.runningMatches(match));
    }

}