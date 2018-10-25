package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UI-tier")
public class PostSignInRouteTest {

    private PostSignInRoute CuT;

    private PlayerLobby playerLobby;
    private PlayerLobby playerLobbyMock;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private GameCenter gameCenter;

    private Player playerMock;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);
        response = mock(Response.class);

        playerLobbyMock = mock(PlayerLobby.class);
        playerLobby = new PlayerLobby();


        CuT = new PostSignInRoute(playerLobby, engine);
    }

    @Test
    public void valid_username() {


        when(request.queryParams(any(String.class))).thenReturn("Ryan");
        playerLobbyMock.addPlayer(new Player("Ryan"));

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

         assertNotNull(CuT.handle(request, response));

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
//

        testHelper.assertViewModelAttribute("title", "Welcome!");
        //testHelper.assertViewModelAttribute();



    }
}
