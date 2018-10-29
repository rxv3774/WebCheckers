package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

         CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //there should not be any errors, valid username
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute("showErrorMessage", "");
    }

    @Test
    public void invalid_username_emptySpaces() {

        when(request.queryParams(any(String.class))).thenReturn("");
        playerLobbyMock.addPlayer(new Player(""));

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Username can not be empty, error message must be present.
        testHelper.assertViewModelAttribute("showErrorMessage",
                "you entered illegal characters in the name. Please enter a different name");

    }

    @Test
    public void invalid_username_specialCharacters() {

        when(request.queryParams(any(String.class))).thenReturn("@#$");
        playerLobbyMock.addPlayer(new Player("@#$"));

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Username can not have special characters, illegal characters error message must be present.
        testHelper.assertViewModelAttribute("showErrorMessage",
                "you entered illegal characters in the name. Please enter a different name");
    }

    @Test
    public void invalid_username_repeatingName() {

        when(request.queryParams(any(String.class))).thenReturn("Ryan");
        playerLobbyMock.addPlayer(new Player("Ryan"));
        playerLobbyMock.addPlayer(new Player("Ryan"));

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Username can not be the username of a player in the same lobby/session, already used name
        // error message must be present.
        testHelper.assertViewModelAttribute("showErrorMessage",
                "you entered an already used name. Please enter a different name");

    }
}
