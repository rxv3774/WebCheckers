package com.webcheckers.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Row implements Iterable<Space> {
    private static final int SPACES = 8;

    private int index, current;
    private Space row[];

    /**
     * Row object of eight spaces
     * @param index: index of current row, within the boundaries of the board
     */
    public Row(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        } else {
            this.index = index;
            this.row = new Space[SPACES];
            for (int i = 0; i < SPACES; i++) {
                row[i] = new Space(index, i);
            }
        }
    }

    /**
     * Initialize spaces in the current row object
     */
    public void initialize() {
        for (Space space : row) {
            space.initialize();
        }
    }

    public Space[] getRow() {
        return row;
    }


    public int getIndex() {
        return index;
    }

    /**
     * checks the color's pieces in each space to see if it has possible moves
     *
     * @param color: active color
     * @param board: board with all the pieces
     * @return true if a piece has a possible move
     */
    public boolean hasPossibleMoves(Piece.Color color, Board board) {
        for (Space space : row) {
            if (space.hasPossibleMoves(color, board))
                return true;
        }
        return false;
    }

    /**
     * Deep copy row, used for AI.
     *
     * @return deepcopy of the row
     */
    public Row deepCopy() {
        Row cp = new Row(this.index);
        for (int i = 0; i < row.length; i++) {
            cp.row[i] = row[i].deepCopy();
        }
        return cp;
    }

    /**
     * and iterator so iterable works
     *
     * @return an Iterator
     */
    @Override
    public Iterator<Space> iterator() {
        current = 0;

        return new Iterator<Space>() {
            @Override
            public boolean hasNext() {
                return current < row.length;
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
     *
     * @return the reversed iterator
     */
    public Iterator<Space> reverseIterator() {
        current = row.length - 1;

        return new Iterator<Space>() {
            @Override
            public boolean hasNext() {
                return current >= 0;
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
