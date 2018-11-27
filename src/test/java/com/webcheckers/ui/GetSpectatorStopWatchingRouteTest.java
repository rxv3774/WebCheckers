package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.HaltException;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetSpectatorStopWatchingRouteTest {
    private GetSpectatorStopWatchingRoute spectatorStopWatchingRoute;

    private Request request;
    private Response response;
    private Session session;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);

        when(request.session()).thenReturn(session);

        spectatorStopWatchingRoute = new GetSpectatorStopWatchingRoute();
    }

    @Test
    void testRedirect() {
        assertThrows(spark.HaltException.class, () -> spectatorStopWatchingRoute.handle(request, response));
    }
}
