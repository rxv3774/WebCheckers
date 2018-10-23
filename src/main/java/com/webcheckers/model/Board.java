package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board implements Iterable<Row>{
    private Row board[];
    private int current;

    public Board(){
        this.board = new Row[8];
        for(int i=0; i<8; i++){
            board[i] = new Row(i);
        }
    }

    @Override
    public Iterator<Row> iterator() {
        current = 0;

        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                if(current < board.length)
                    return true;
                else
                    return false;
            }

            @Override
            public Row next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Row temp = board[current];
                current++;
                return temp;
            }
        };
    }

    public int getRowsSize() {
        return board.length;
    }
}
