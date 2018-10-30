package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board implements Iterable<Row>{
    private Row board[];
    private int current;

    private int rowArraySize = 8;
    private int Init_Rows = 3;

    public Board(){
        this.board = new Row[rowArraySize];
        for(int i=0; i<rowArraySize; i++){
            board[i] = new Row(i);
        }
    }

    /**
     * Initialize the game.
     * @param color the color to initilize the pieces for
     */
    public void initialize(Piece.Color color){
        if(color == Piece.Color.RED){
            for(int i = 0; i <= Init_Rows; i++){
                board[i].initialize();
            }
        }
        if(color == Piece.Color.WHITE){
            for(int i = rowArraySize - 1; i >= rowArraySize-Init_Rows; i--){
                board[i].initialize();
            }
        }
    }

    /**
     * and iterator so iterable works
     * @return an Iterator
     */
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

    /**
     * reverse the iterator so that we can display the other side fo the board easily
     * @return the reversed iterator
     */
    public Iterator<Row> reverseIterator() {
        current = board.length-1;

        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                if(current >= 0)
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
                current--;
                return temp;
            }
        };
    }

    public int getRowsSize() {
        return board.length;
    }
}
