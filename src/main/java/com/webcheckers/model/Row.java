package com.webcheckers.model;

import java.util.Iterator;

public class Row implements Iterable<Space> {
    private int index;

    public Row(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        } else {
            this.index = index;
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Iterator<Space> iterator() {
        return null;
    }
}
