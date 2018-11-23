package com.webcheckers.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board implements Iterable<Row> {
    private Row board[];
    private int current;

    private int rowArraySize = 8;
    private int Init_Rows = 3;

    public Board() {
        this.board = new Row[rowArraySize];
        for (int i = 0; i < rowArraySize; i++) {
            board[i] = new Row(i);
        }
    }

    /**
     * Initialize the game.
     *
     * @param color the color to initilize the pieces for
     */
    public void initialize(Piece.Color color) {
        if (color == Piece.Color.RED) {
            for (int i = 0; i <= Init_Rows; i++) {
                board[i].initialize();
            }
        }
        if (color == Piece.Color.WHITE) {
            for (int i = rowArraySize - 1; i >= rowArraySize - Init_Rows; i--) {
                board[i].initialize();
            }
        }
    }

    /**
     * and iterator so iterable works
     *
     * @return an Iterator
     */
    @Override
    public Iterator<Row> iterator() {
        current = 0;

        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                if (current < board.length)
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
     *
     * @return the reversed iterator
     */
    public Iterator<Row> reverseIterator() {
        current = board.length - 1;

        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                if (current >= 0)
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

    /**
     * checks the color's pieces in each row to see if it has possible moves
     * @param color: active color
     * @return true if a piece has a possible move
     */
    public boolean hasPossibleMoves(Piece.Color color){
        for (Row row: board){
            if(row.hasPossibleMoves(color, this))
                return true;
        }
        return false;
    }

    /**
     * get the space object at the given position
     *
     * @param position: coordinates of the desired space
     * @return Space object
     */
    public Space getSpace(Position position){
        int row = position.getRow();
        int col = position.getCell();
        for(Row r: board){
            if(r.getIndex() == row){
                for(Space space: r.getRow()){
                    if(space.getCellIdx() == col)
                        return space;
                }
            }
        }
        return null;
    }

    /**
     * check if there is a piece on the board at the given position
     *
     * @param position: coordinates of the desired space
     * @return true if space has a piece on it
     */
    public boolean spaceHasPiece(Position position){
        Space space = this.getSpace(position);
        return space.hasPiece();
    }

    /**
     * check if there is an enemy piece at the given position on the board
     *
     * @param position: coordinates of desired space
     * @param isRedPlayer: if the current player (not the enemy) is red
     * @return true if space has an enemy piece on it
     */
    public boolean spaceHasEnemyPiece(Position position, boolean isRedPlayer){
        Space space = this.getSpace(position);
        if(space.hasPiece()){
            if(isRedPlayer){
                return (space.getPieceColor() == Piece.Color.WHITE);
            } else {
                return (space.getPieceColor() == Piece.Color.RED);
            }
        }else
            return false;
    }

    /**
     * remove the piece at the given position, usually follows a jump move.
     *
     * @param position: coordinates of desired space
     * @return removed piece
     */
    public Piece removePieceAtPosition(Position position){
        Space space = this.getSpace(position);
        return space.removePiece();
    }

    public int getRowsSize() {
        return board.length;
    }
}
