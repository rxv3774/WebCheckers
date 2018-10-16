package com.webcheckers.model;

public class Row {
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
}
