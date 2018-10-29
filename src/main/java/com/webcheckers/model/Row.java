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

    /**
     * Initialize.
     * Initialize spaces.
     */
    public void initialize(){
        for(Space space : row){
            space.initialize();
        }
    }

    /**
     * Get space at col.
     * @param col the col
     * @return the space
     */
    public Space getSpace(int col){
        return row[col];
    }

    public int getIndex() { return index; }

    /**
     * and iterator so iterable works
     * @return an Iterator
     */
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

    /**
     * reverse the iterator so that we can display the other side fo the board easily
     * @return the reversed iterator
     */
    public Iterator<Space> reverseIterator() {
        current = row.length-1;

        return new Iterator<Space>() {
            @Override
            public boolean hasNext() {
                if(current >= 0)
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
                current--;
                return temp;
            }
        };
    }
}
