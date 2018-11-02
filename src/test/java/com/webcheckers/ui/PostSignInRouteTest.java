package com.webcheckers.ui;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostSignInRouteTest {

    //Error messages given by the view-model.
    private PostSignInRoute CuT;
    private final String SHOW_ERROR_MESSAGE = "showErrorMessage";
    private final String ILLEGAL_CHARACTER_MESSAGE =
            "The name you entered contains illegal characters.";
    private final String REPEATING_NAMES_ERROR_MESSAGE = "That name is already in use";
    private final String MESSAGE_TYPE = "messageType";
    private final String ERROR_TYPE = "error";

    private PlayerLobby playerLobby;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private Player playerMock;

    /**
     * SetUp new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);
        response = mock(Response.class);
        playerLobby = new PlayerLobby();
        CuT = new PostSignInRoute(playerLobby, engine);
    }

    /**
     * Test ensures that when a valid username is given, that the Player gets added into the player lobby with no issues.
     */
    @Test
    public void valid_username() {
        playerMock = new Player("Ryan");
        when(request.queryParams(any(String.class))).thenReturn("Ryan");
        playerLobby.addPlayer(playerMock);

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Player name should be valid, so the player should be added into the playerLobby.
        assertTrue(playerLobby.getPlayersNamesAsArrayList().contains("Ryan"));
    }

    /**
     * Test ensures that when a username made up of an empty space is entered, that it returns an illegal character error.
     */
    @Test
    public void invalid_username_emptySpaces() {
        playerMock = new Player("");
        when(request.queryParams(any(String.class))).thenReturn("");
        playerLobby.addPlayer(playerMock);

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Username can not be empty, error message must be present.
        testHelper.assertViewModelAttribute(MESSAGE_TYPE, ERROR_TYPE);
        testHelper.assertViewModelAttribute(SHOW_ERROR_MESSAGE, ILLEGAL_CHARACTER_MESSAGE);
    }

    /**
     * Test ensures that when a username contains a special character, that it returns an illegal character error.
     */
    @Test
    public void invalid_username_specialCharacters() {
        when(request.queryParams(any(String.class))).thenReturn("@#$");
        playerLobby.addPlayer(new Player("@#$"));

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Username can not have special characters, illegal characters error message must be present.
        testHelper.assertViewModelAttribute(MESSAGE_TYPE, ERROR_TYPE);
        testHelper.assertViewModelAttribute(SHOW_ERROR_MESSAGE, ILLEGAL_CHARACTER_MESSAGE);
    }

    /**
     * Test ensures that when a username that has been previously used is entered, that it returns a repeated username error.
     */
    @Test
    public void invalid_username_repeatingName() {
        when(request.queryParams(any(String.class))).thenReturn("Ryan");
        when(request.queryParams(any(String.class))).thenReturn("Ryan");
        playerLobby.addPlayer(new Player("Ryan"));
        playerLobby.addPlayer(new Player("Ryan"));

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        //Analyze the results
        //  * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //Username can not be the username of a player in the same lobby/session, already used name
        // error message must be present.
        testHelper.assertViewModelAttribute(MESSAGE_TYPE, ERROR_TYPE);
        testHelper.assertViewModelAttribute(SHOW_ERROR_MESSAGE, REPEATING_NAMES_ERROR_MESSAGE);
    }
}