package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Match;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link PostCheckTurnRouteTest} component
 */
@Tag("UI-tier")
public class PostCheckTurnRouteTest {


    private static final String NAME_ATTR = "name";


    PostCheckTurnRoute CuT;

    PlayerLobby playerLobby;
    Request request;
    Response response;
    Session session;
    Gson gson;


    @BeforeEach
    void setup() {

        gson = new Gson();

        request = mock(Request.class);
        response = mock(Response.class);
        playerLobby = mock(PlayerLobby.class);

        session = mock(Session.class);
        when(request.session()).thenReturn(session);

        CuT = new PostCheckTurnRoute(gson, playerLobby);
    }


    @Test
    public void handlePlayerIsNull() {

        when(session.attribute(NAME_ATTR)).thenReturn(null);
        String currentPlayerName = null;
        when( playerLobby.getUserObject( currentPlayerName ) ).thenReturn( null );

        //Test1 this checks to make sure that the handle returns a null.
        assertNull(CuT.handle(request, response));
    }


    @Test
    public void handleGameIsNull() {

        Player p1 = new Player("Smith");

        when(session.attribute(NAME_ATTR)).thenReturn(p1.getName());
        String currentPlayerName = p1.getName();
        when( playerLobby.getUserObject( currentPlayerName ) ).thenReturn( p1 );

        //Test1 This should return null because the player isn't in a match
        assertEquals(gson.toJson(Message.OPPONENT_RESIGN), CuT.handle(request, response));
    }

    @Test
    public void handleActivePlayer() {

        Player p1 = new Player("Rick");
        Player p2 = new Player("Morty");

        GameCenter gameCenter = new GameCenter();

        gameCenter.createGame(p1, p2);

        System.out.println(p2.getMatch() != null);

        when(session.attribute(NAME_ATTR)).thenReturn(p1.getName());
        String currentPlayerName = p1.getName();
        when( playerLobby.getUserObject( currentPlayerName ) ).thenReturn( p1 );

        //Test1 This checks if the current player is the active player
        assertEquals(gson.toJson(Message.TRUE), CuT.handle(request, response));
    }


    @Test
    public void handleHasWinner() {

        Player p2 = mock(Player.class);

        final String p2Name = "Potter";

        Match game = mock(Match.class);
        when(game.hasWinner()).thenReturn(true);

        when(p2.getName()).thenReturn(p2Name);
        when(p2.getMatch()).thenReturn(game);

        when(session.attribute(NAME_ATTR)).thenReturn(p2Name);
        String currentPlayerName = p2Name;
        when( playerLobby.getUserObject( currentPlayerName ) ).thenReturn( p2 );

        Player player = mock(Player.class);
        when(player.getMatch()).thenReturn(game);

        //Test1 This checks if their is a winner
        assertEquals(gson.toJson(Message.TRUE), CuT.handle(request, response));
    }


    @Test
    public void handlePlayerIsntActive() {

        Player p1 = new Player("Jelly");
        Player p2 = new Player("Bob");

        GameCenter gameCenter = new GameCenter();
        gameCenter.createGame(p1, p2);

        when(session.attribute(NAME_ATTR)).thenReturn(p2.getName());
        String currentPlayerName = p2.getName();
        when( playerLobby.getUserObject( currentPlayerName ) ).thenReturn( p2 );

        //Test1 Player isn't the active player so it's null
        assertEquals(gson.toJson(Message.FALSE), CuT.handle(request, response));
    }
}