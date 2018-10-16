package com.webcheckers.model;

import java.util.Iterator;

public class BoardView implements Iterable<Row> {
    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Row next() {
                return null;
            }
        };
    }
}
