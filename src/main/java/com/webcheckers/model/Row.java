package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Row implements Iterable<Space> {
    private int index, current;
    private ArrayList<Space> spaces = new ArrayList<>();

    public Row(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException();
        } else {
            this.index = index;
            for(int i=0; i<8; i++){
                if(index<3) {
                    if(index%2 == 0) {
                        if(i%2 == 1)
                            spaces.add(new Space(i, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE)));
                        else
                            spaces.add(new Space(i, null));
                    }
                    else if(index%2 == 1){
                        if(i%2 == 0)
                            spaces.add(new Space(i, new Piece(Piece.Type.SINGLE, Piece.Color.WHITE)));
                        else
                            spaces.add(new Space(i, null));
                    }
                }
                else if (index>=5)
                    if(index%2 == 0) {
                        if(i%2 == 1)
                            spaces.add(new Space(i, new Piece(Piece.Type.SINGLE, Piece.Color.RED)));
                        else
                            spaces.add(new Space(i, null));
                    }
                    else if(index%2 == 1){
                        if(i%2 == 0)
                            spaces.add(new Space(i, new Piece(Piece.Type.SINGLE, Piece.Color.RED)));
                        else
                            spaces.add(new Space(i, null));
                    }
                else
                    spaces.add(new Space(i, null));

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
                if(current < spaces.size())
                    return true;
                else
                    return false;
            }

            @Override
            public Space next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Space temp = spaces.get(current);
                current++;
                return temp;
            }
        };
    }
}
