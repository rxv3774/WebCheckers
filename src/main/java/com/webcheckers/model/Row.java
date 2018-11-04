package com.webcheckers.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Row implements Iterable<Space> {
    private int index, current;
    private Space row[];

    private int spaceArraySize = 8;

    public Row(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        } else {
            this.index = index;
            this.row = new Space[spaceArraySize];
            for (int i = 0; i < spaceArraySize; i++) {
                row[i] = new Space(i, index);
            }
        }
    }

    /**
     * Initialize.
     * Initialize spaces.
     */
    public void initialize() {
        for (Space space : row) {
            space.initialize();
        }
    }

    /**
     * Get space at col.
     *
     * @param col the col
     * @return the space
     */
    public Space getSpace(int col) {
        return row[col];
    }

    public int getIndex() {
        return index;
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
                if (current < row.length)
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
     *
     * @return the reversed iterator
     */
    public Iterator<Space> reverseIterator() {
        current = row.length - 1;

        return new Iterator<Space>() {
            @Override
            public boolean hasNext() {
                if (current >= 0)
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


//    /**
//     * Deep copy row.
//     *
//     * @return deepcopy of the row
//     */
//    public Row deepCopy() {
//        Row cp = new Row(this.index);
//        for (int i = 0; i < row.length; i++) {
//            cp.row[i] = row[i].deepCopy();
//        }
//        return cp;
//    }
}
