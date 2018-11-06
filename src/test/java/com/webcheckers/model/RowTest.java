package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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

    /*
     * Tests that the Iterator hasNext functions correctly
     */
    @Test
    void test_hasNext() {
        final Row row = new Row(1);
        Iterator itr = row.iterator();
        assertTrue(itr.hasNext());
    }

    /*
     * Tests that the Iterator next functions correctly
     */
    @Test
    void test_next() {
        final Row row = new Row(1);
        Iterator itr = row.iterator();
        assertNotNull(itr.next());
        row.initialize();
        assertTrue(itr.next() instanceof Space);
    }

    /*
     * Tests that the Reverse Iterator hasNext functions correctly
     */
    @Test
    public void test_hasNextReverse(){
        final Row row = new Row(1);
        Iterator itr = row.reverseIterator();
        assertTrue(itr.hasNext());
    }

    /*
     * Tests that the Reverse Iterator next functions correctly
     */
    @Test
    void test_nextReverse() {
        final Row row = new Row(1);
        Iterator itr = row.reverseIterator();
        assertNotNull(itr.next());
        row.initialize();
        assertTrue(itr.next() instanceof Space);
    }
}