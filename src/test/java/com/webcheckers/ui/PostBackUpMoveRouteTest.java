package com.webcheckers.ui;


import com.google.gson.Gson;
import com.webcheckers.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link PostBackUpMoveRoute} component
 */
@Tag("UI-tier")
public class PostBackUpMoveRouteTest {

    private Gson gson;
    private Request request;
    private Response response;

    @BeforeEach
    public void setup() {

        gson = new Gson(); // can't make a mock object of Gson

        request = mock( Request.class );
        response = mock( Response.class );
    }

    @Test
    public void handleWorks(){
        PostBackUpMoveRoute CuT = new PostBackUpMoveRoute( gson );

        //Test1 This checks to make sure the handle doesn't return a null.
        assertNotNull( CuT.handle( request, response) ) ;

        //Test2 This checks to make sure it returns the correct value.
        assertEquals( gson.toJson(Message.BACKUPMOVE) , CuT.handle( request, response) );

    }

}