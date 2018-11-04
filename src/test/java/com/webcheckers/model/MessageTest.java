package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("Model-tier")
public class MessageTest {




    @Test
    public void MessageWorks(){
        //Test1 not a null constructor
        assertNotNull( new Message( "error 404", Message.Type.error ) );
    }

    @Test
    public void getTextWorks(){
        Message message = new Message( "error 404", Message.Type.error );

        //Test1 doesn't return a null value
        assertNotNull( message.getText() );

        //Test1  the message text matches
        assertEquals( "error 404", message.getText() );

    }

    @Test
    public void getTypeWorks(){
        Message message = new Message( "error 404", Message.Type.error );

        //Test1 doesn't return a null
        assertNotNull( message.getType() );

        //Test2 returns the correct type
        assertEquals( Message.Type.error, message.getType() );

    }


}