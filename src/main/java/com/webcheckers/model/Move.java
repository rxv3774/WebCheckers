package com.webcheckers.model;

/**
 * The type Move.
 *
 * @author toothlessG help with GSON, store the move in this class
 */
public class Move {

    private position start;
    private position end;

    /**
     * Instantiates a new Move.
     *
     * @param start the start space
     * @param end   the end space
     */
    public Move(position start, position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Move.
     *
     * @param start the start space
     * @param end   the end space
     * @param board the board to make move on
     */
    public Move(position start, position end, Board board) {
        this.start = start;
        this.end = end;
    }

    public position getStart() {
        return start;
    }

    /**
     * Make move.
     */
    public void makeMove(Board board){
        Space sStart = board.getSpace(start.getRow(), start.getCol());
        Space sEnd = board.getSpace(end.getRow(), end.getCol());
        sStart.movePieceTo(sEnd);
    }

    /**
     * string representation of the move
     *
     * @return string rep
     */
    public String toString() {
        return "Start: (" + start.getRow() + "," + start.getCol() + ") End: (" + end.getRow() + "," + end.getCol() + ")";
        //return null;
    }

    /**
     * Checks equality by positioning of the start and end spaces of the move
     *
     * @param other - Other move to check with
     * @return If the moves are equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Move)) return false;
        Move move = (Move) other;
        return this.start.getRow() == move.start.getRow() &&
                this.start.getCol() == move.start.getCol() &&
                this.end.getRow() == move.end.getRow() &&
                this.end.getCol() == move.end.getCol();
    }
}
