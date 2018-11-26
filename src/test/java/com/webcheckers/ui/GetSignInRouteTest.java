package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link GetSignInRoute} component
 */
@Tag("UI-tier")
class GetSignInRouteTest {
    private static final String VIEW_NAME = "signin.ftl";

    private static final String TITLE_ATTR = "title";

    private static final String TITLE = "Sign-In";

    private GetSignInRoute getSignInRoute;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        // create a unique getSignInRoute for each test
        getSignInRoute = new GetSignInRoute(engine);
    }

    /**
     * Test that getSignInRoute shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        getSignInRoute.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(TITLE_ATTR, TITLE);
        //   * test view name
        testHelper.assertViewName(VIEW_NAME);
    }


}