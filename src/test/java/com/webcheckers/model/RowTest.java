package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
class RowTest {

    @Test
    public void constructorWorks(){

        //Test1 not null
        assertNotNull( new Row(1) );

        //Test2 throws an exception index < 0
        assertThrows( IllegalArgumentException.class, () -> new Row(-10) );

        //Test3 throws an exception index > 7
        assertThrows( IllegalArgumentException.class, () -> new Row(10) );
    }


    @Test
    public void getIndexWorks(){

        //Test1 tests to see if the returned value isn't null
        assertNotNull( new Row( 2).getIndex() );

        //Test2 looks to see if it gets the correct number
        assertEquals(2, new Row( 2).getIndex() );
    }

    @Test
    public void iteratorWorks(){
        Row row = new Row(2);

        //Test1 doesn't return Null
        assertNotNull( row.iterator() );
    }

    @Test
    public void reverseIteratorWorks(){
        Row row = new Row(2);

        //Test1 doesn't return Null
        assertNotNull( row.reverseIterator() );
    }
}