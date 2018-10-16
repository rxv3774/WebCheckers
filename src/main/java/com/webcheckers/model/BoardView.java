package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardView implements Iterable<Row> {
    private ArrayList<Row> rows = new ArrayList<>();
    private int current;

    public BoardView(){
        for(int i=0; i<8; i++){
            rows.add(new Row(i));
        }
    }

    @Override
    public Iterator<Row> iterator() {
        current = 0;

        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                if(current < rows.size())
                    return true;
                else
                    return false;
            }

            @Override
            public Row next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Row temp = rows.get(current);
                current++;
                return temp;
            }
        };
    }

    public int getRowsSize() {
        return rows.size();
    }
}
