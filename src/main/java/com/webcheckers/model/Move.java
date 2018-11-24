package com.webcheckers.model;

/**
 * The type Move.
 *
 * @author toothlessG help with GSON, store the move in this class
 */
public class Move {

    private Position start;
    private Position end;

    /**
     * Instantiates a new Move.
     *
     * @param start the start space
     * @param end   the end space
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getEnd() {
        return end;
    }

    /**
     * Make move.
     *
     * @param board: board object to get retrieve spaces from
     */
    public void makeMove(Board board) {
        Space sStart = board.getSpace(start);
        Space sEnd = board.getSpace(end);
        sStart.movePieceTo(sEnd);
        if (isJumpMove()) {
            Position middle = start.getMiddle(end);
            board.removePieceAtPosition(middle);
        }
    }

    /**
     * check is the move performed is valid
     *
     * @param board:       board object to check spaces from
     * @param isRedPlayer: if the current player is red
     * @return true if the move is valid
     */
    public boolean isValid(Board board, boolean isRedPlayer) {
        if (end.isOutOfBounds())
            return false;
        if (isSingleMove()) {
            if (!board.spaceHasPiece(end)) {
                if (isKingMove(board))
                    return movingBackwards(isRedPlayer);
                else
                    return true;
            } else
                return false;
        } else if (isJumpMove()) {
            Position middle = start.getMiddle(end);
            if (!board.spaceHasPiece(end) && board.spaceHasEnemyPiece(middle, isRedPlayer)) {
                if (isKingMove(board))
                    return movingBackwards(isRedPlayer);
                else
                    return true;
            } else
                return false;
        } else {
            System.out.println("failed");
            return false;
        }
    }

    public boolean isKingMove(Board board) {
        Space space = board.getSpace(start);
        return !space.hasKingPiece();
    }

    /**
     * Calculate difference between start and end position to see if it's a single move
     *
     * @return true if single move
     */
    public boolean isSingleMove() {
        return Math.abs(start.getRow() - end.getRow()) == 1 && Math.abs(start.getCell() - end.getCell()) == 1;
    }

    /**
     * Calculate difference between start and end position to see if it's a single move
     *
     * @return true if single move
     */
    public boolean isJumpMove() {
        return Math.abs(start.getRow() - end.getRow()) == 2 && Math.abs(start.getCell() - end.getCell()) == 2;
    }

    /**
     * Check if piece is moving backwards
     *
     * @param isRedPiece if the moved piece is red or not
     * @return boolean if piece move is backward
     */
    public boolean movingBackwards(boolean isRedPiece) {
        if (isRedPiece)
            return (start.getRow() - end.getRow()) <= 0;
        return (end.getRow() - start.getRow()) <= 0;
    }

    /**
     * string representation of the move
     *
     * @return string rep
     */
    public String toString() {
        return String.format("Start: (%d, %d); End: (%d, %d)",
                start.getRow(), start.getCell(), end.getRow(), end.getCell());
    }

    /**
     * Checks equality by positioning of the start and end spaces of the move
     *
     * @param other - Other move to check with
     * @return If the moves are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Move) {
            Move move = (Move) other;
            return this.start.getRow() == move.start.getRow() &&
                    this.start.getCell() == move.start.getCell() &&
                    this.end.getRow() == move.end.getRow() &&
                    this.end.getCell() == move.end.getCell();
        } else return false;
    }
}
