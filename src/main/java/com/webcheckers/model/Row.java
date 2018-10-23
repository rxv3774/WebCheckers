package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Row implements Iterable<Space> {
    private int index, current;
    private Space row[];

    public Row(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        } else {
            this.index = index;
            this.row = new Space[8];
            for(int i=0; i<8; i++){
                row[i] = new Space(i, index);
            }
        }
    }

    public int getIndex() { return index; }

    @Override
    public Iterator<Space> iterator() {
        current = 0;

        return new Iterator<Space>() {
            @Override
            public boolean hasNext() {
                if(current < row.length)
                    return true;
                else
                    return false;
            }

            @Override
            public Space next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Space temp = row[current];
                current++;
                return temp;
            }
        };
    }
}
