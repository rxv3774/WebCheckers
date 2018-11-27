package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Board implements Iterable<Row> {
    private static final int FILLED_ROWS = 3;
    private static final int ROWS = 8;

//    private Space[][] spaces = new Space[ROWS][ROWS];

    private Row board[];
    private int current;

    public Board() {
        this.board = new Row[ROWS];
        for (int i = 0; i < ROWS; i++) {
            board[i] = new Row(i);
        }
    }

    /**
     * Initialize the game.
     *
     * @param color the color to initialize the pieces for
     */
    public void initialize(Piece.Color color) {
        if (color == Piece.Color.RED) {
            for (int i = 0; i <= FILLED_ROWS; i++) {
                board[i].initialize();
            }
        }
        if (color == Piece.Color.WHITE) {
            for (int i = ROWS - 1; i >= ROWS - FILLED_ROWS; i--) {
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
                return current < board.length;
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
                return current >= 0;
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
     *
     * @param color: active color
     * @return true if a piece has a possible move
     */
    public boolean hasPossibleMoves(Piece.Color color) {
        for (Row row : board) {
            if (row.hasPossibleMoves(color, this))
                return true;
        }
        return false;
    }

    /**
     * Gets possible moves.
     *
     * @param color the color
     * @return the possible moves for color player
     */
    public ArrayList<Move> getPossibleMoves(Piece.Color color) {
        ArrayList<Move> moves = new ArrayList<>();
        for (Row row : board) {
            for (Space sp : row) {
                if (sp.hasPiece() && sp.getPieceColor() == color) {
                    moves.addAll(sp.getPossibleMoves(color, this));
                }
            }
        }
        if (this.isJumpAvailable(moves)) {
            moves.removeIf(m -> !m.isJumpMove());
        }
        return moves;
    }

    /**
     * Is jump availible.
     *
     * @param moves: possible, valid moves
     * @return true if color player has a jump availible
     */
    public boolean isJumpAvailable(List<Move> moves) {
        for (Move move : moves) {
            if (move.isJumpMove()) {
                return true;
            }
        }
        return false;
    }

    /**
     * get the space object at the given position
     *
     * @param position: coordinates of the desired space
     * @return Space object
     */
    public Space getSpace(Position position) {
        int row = position.getRow();
        int col = position.getCell();
        for (Row r : board) {
            if (r.getIndex() == row) {
                for (Space space : r.getRow()) {
                    if (space.getCellIdx() == col)
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
    public boolean spaceHasPiece(Position position) {
        Space space = this.getSpace(position);
        return space.hasPiece();
    }

    /**
     * check if there is an enemy piece at the given position on the board
     *
     * @param position:    coordinates of desired space
     * @param isRedPlayer: if the current player (not the enemy) is red
     * @return true if space has an enemy piece on it
     */
    public boolean spaceHasEnemyPiece(Position position, boolean isRedPlayer) {
        Space space = this.getSpace(position);
        if (space.hasPiece()) {
            if (isRedPlayer) {
                return (space.pieceColorMatch(Piece.Color.WHITE));
            } else {
                return (space.pieceColorMatch(Piece.Color.RED));
            }
        } else
            return false;
    }

    /**
     * remove the piece at the given position, usually follows a jump move.
     *
     * @param position: coordinates of desired space
     * @return removed piece
     */
    public void removePieceAtPosition(Position position) {
        Space space = this.getSpace(position);
        space.removePiece();
    }

    /**
     * Strictly for unit testing
     * @return size of rows array
     */
    public int getRowsSize() {
        return board.length;
    }


    /**
     * Deep copy game board, used for AI.
     *
     * @return a deep copy of the game board
     */
    public Board deepCopy() {
        Board cp = new Board();
        for (int i = 0; i < board.length; i++) {
            cp.board[i] = board[i].deepCopy();
        }
        return cp;
    }
}
