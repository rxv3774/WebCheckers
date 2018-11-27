package com.webcheckers.model;

import java.util.Arrays;
import java.util.List;

public class Space {

    private int cellIdx, rowIdx;
    private Piece piece;

    public Space(int rowIdx, int cellIdx) {
        this.cellIdx = cellIdx;
        this.rowIdx = rowIdx;
        this.piece = null;
    }

    public Space(int cellIdx, int rowIdx, Piece piece) {
        this.cellIdx = cellIdx;
        this.rowIdx = rowIdx;
        this.piece = piece;
    }

    /**
     * Initializes the space if it is valid to have a starting piece on it
     */
    public void initialize() {
        Piece piece = null;
        if (isValid()) {
            if (rowIdx < 3) {
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
            }
            if (rowIdx > 4) {
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
            }
        }
        this.piece = piece;
    }

    /**
     * Get the Column index of the Space
     *
     * @return the column index
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /**
     * Get the Row index of the Space
     *
     * @return the row index
     */
    public int getRowIdx() {
        return rowIdx;
    }

    /**
     * check if the color matches the piece
     *
     * @param color color to check if it matches
     * @return true if color matches false if not
     */
    public boolean pieceColorMatch(Piece.Color color) {
        if (piece == null) return false;
        return piece.getColor() == color;
    }

    /**
     * get the color of piece
     *
     * @return color of the piece
     */
    public Piece.Color getPieceColor() {
        if (piece == null) return null;
        return piece.getColor();
    }

    /**
     * check space's piece to see if it has possible moves
     *
     * @param color  color of active player
     * @param board: board with all the pieces
     * @return true if piece at space has possible moves
     */
    @SuppressWarnings("Duplicates")
    public boolean hasPossibleMoves(Piece.Color color, Board board) {
        if (pieceColorMatch(color)) { // checks if color matches the piece.
            Position start = new Position(rowIdx, cellIdx); // finds the starting position.
            Move singleUR, singleUL, singleDR, singleDL, jumpUR, jumpUL, jumpDR, jumpDL;
            if (color == Piece.Color.WHITE) {
                singleUR = new Move(start, new Position(rowIdx - 1, cellIdx + 1)); // creates the move for an up-right single move.
                singleUL = new Move(start, new Position(rowIdx - 1, cellIdx - 1));
                jumpUR = new Move(start, new Position(rowIdx - 2, cellIdx + 2)); // creates the moves for an up-right jump.
                jumpUL = new Move(start, new Position(rowIdx - 2, cellIdx - 2));
                if (piece.isKing()) {
                    singleDR = new Move(start, new Position(rowIdx + 1, cellIdx + 1)); // creates the move for an down-right single move.
                    singleDL = new Move(start, new Position(rowIdx + 1, cellIdx - 1));
                    jumpDR = new Move(start, new Position(rowIdx + 2, cellIdx + 2)); // creates the move for an down-right jump.
                    jumpDL = new Move(start, new Position(rowIdx + 2, cellIdx - 2));
                    List<Move> moves = Arrays.asList(singleUR, singleUL, singleDR, singleDL, jumpUR, jumpUL, jumpDR, jumpDL); // contains all moves a king piece can use in a game.
                    return possibleMovesHelper(moves, board, false); // ensures all moves contained are valid.
                } else {
                    List<Move> moves = Arrays.asList(singleUR, singleUL, jumpUR, jumpUL); // contains all moves a single piece can use in a game
                    return possibleMovesHelper(moves, board, false);
                }
            } else {
                singleUR = new Move(start, new Position(rowIdx + 1, cellIdx + 1));
                singleUL = new Move(start, new Position(rowIdx + 1, cellIdx - 1));
                jumpUR = new Move(start, new Position(rowIdx + 2, cellIdx + 2));
                jumpUL = new Move(start, new Position(rowIdx + 2, cellIdx - 2));
                if (piece.isKing()) {
                    singleDR = new Move(start, new Position(rowIdx - 1, cellIdx + 1));
                    singleDL = new Move(start, new Position(rowIdx - 1, cellIdx - 1));
                    jumpDR = new Move(start, new Position(rowIdx - 2, cellIdx + 2));
                    jumpDL = new Move(start, new Position(rowIdx - 2, cellIdx - 2));
                    List<Move> moves = Arrays.asList(singleUR, singleUL, singleDR, singleDL, jumpUR, jumpUL, jumpDR, jumpDL);
                    return possibleMovesHelper(moves, board, true);
                } else {
                    List<Move> moves = Arrays.asList(singleUR, singleUL, jumpUR, jumpUL);
                    return possibleMovesHelper(moves, board, true);
                }
            }
        } else
            return false;
    }

    /**
     * Checks to see if there is a valid jump move available, used to check for required double jump
     *
     * @param color: color of the current player
     * @param board: board with all of the pieces
     * @return true if there is a jump available
     */
    public boolean hasSecondJumpAvailable(Piece.Color color, Board board, Move pendingMove) {
        Position start = new Position(rowIdx, cellIdx);
        Move jumpUR, jumpUL, jumpDR, jumpDL; // similar to previous method however, only need to check for jumps.
        if (color == Piece.Color.WHITE) {
            jumpUR = new Move(start, new Position(rowIdx - 2, cellIdx + 2));
            jumpUL = new Move(start, new Position(rowIdx - 2, cellIdx - 2));
            if (pendingMove.isKingMove(board)) {
                jumpDR = new Move(start, new Position(rowIdx + 2, cellIdx + 2));
                jumpDL = new Move(start, new Position(rowIdx + 2, cellIdx - 2));
                List<Move> moves = Arrays.asList(jumpUR, jumpUL, jumpDR, jumpDL);
                return possibleSecondJumpHelper(moves, board, false, pendingMove);
            } else {
                List<Move> moves = Arrays.asList(jumpUR, jumpUL);
                return possibleSecondJumpHelper(moves, board, false, pendingMove);
            }
        } else {
            jumpUR = new Move(start, new Position(rowIdx + 2, cellIdx + 2));
            jumpUL = new Move(start, new Position(rowIdx + 2, cellIdx - 2));
            if (pendingMove.isKingMove(board)) {
                jumpDR = new Move(start, new Position(rowIdx - 2, cellIdx + 2));
                jumpDL = new Move(start, new Position(rowIdx - 2, cellIdx - 2));
                List<Move> moves = Arrays.asList(jumpUR, jumpUL, jumpDR, jumpDL);
                return possibleSecondJumpHelper(moves, board, true, pendingMove);
            } else {
                List<Move> moves = Arrays.asList(jumpUR, jumpUL);
                return possibleSecondJumpHelper(moves, board, true, pendingMove);
            }
        }
    }

    /**
     * helper method for possible moves, checks if moves are valid
     *
     * @param moves: list of possible moves to check
     * @param board: board with all the pieces
     * @param isRed: if the moved piece is red
     * @return true if any of the moves are valid
     */
    public boolean possibleMovesHelper(List<Move> moves, Board board, boolean isRed) {
        for (Move move : moves) {
            if (move.isValid(board, isRed))
                return true;
        }
        return false;
    }

    /**
     * helper method for possible second jump move, checks if moves are valid
     *
     * @param moves: list of possible moves to check
     * @param board: board with all the pieces
     * @param isRed: if the moved piece is red
     * @return true if any of the moves are valid
     */
    public boolean possibleSecondJumpHelper(List<Move> moves, Board board, boolean isRed, Move pendingMove) {
        for (Move move : moves) {
            if (move.isValidSecondMove(board, isRed, pendingMove))
                return true;
        }
        return false;
    }

    /**
     * Checks to see if the space is black and/or already has a piece on it
     * If it is an open, black space it returns true
     * otherwise returns false
     *
     * @return true if the space can have a piece on it.
     */
    public boolean isValid() {
        if (rowIdx % 2 == 0) {
            return cellIdx % 2 == 1 && piece == null;
        } else {
            return cellIdx % 2 == 0 && piece == null;
        }
    }

    /**
     * Move piece in the space to space space.
     *
     * @param space the space
     */
    public void movePieceTo(Space space) {
        if (space.isKingRow() && !this.piece.isKing()) {
            space.piece = this.piece;
            space.piece.makeKing();
            this.piece = null;
        } else {
            space.piece = this.piece;
            this.piece = null;
        }
    }

    /**
     * check if space's row is a king row
     *
     * @return true if the row will create a king
     */
    public boolean isKingRow() {
        return (this.rowIdx == 0 || this.rowIdx == 7);
    }

    public boolean hasKingPiece() {
        if (this.hasPiece())
            return piece.isKing();
        return false;
    }

    /**
     * remove the piece in this space
     *
     * @return the piece that previously occupied the space
     */
    public Piece removePiece() {
        Piece tmp = this.piece;
        this.piece = null;
        return tmp;
    }


    @Override
    public String toString() {
        return String.format("Space{cellIdx=%d, rowIdx=%d}", cellIdx, rowIdx);
    }

    /**
     * Check if space has a piece
     *
     * @return true if space has a piece false if not.
     */
    public boolean hasPiece() {
        return this.piece != null;
    }

    public Piece getPiece() {
        return this.piece;
    }
}
