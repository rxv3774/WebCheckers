package com.webcheckers.model;

import java.util.List;

/**
 * The type Move.
 *
 * @author toothlessG help with GSON, store the move in this class
 */
public class Move {

    private Space start;
    private Space end;

    /**
     * Instantiates a new Move.
     *
     * @param start the start space
     * @param end   the end space
     */
    public Move(Space start, Space end) {
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
    public Move(Space start, Space end, Board board) {
        this.start = start;
        this.end = end;
    }


    /**
     * string representation of the move
     *
     * @return string rep
     */
    public String toString() {
        return "Start: (" + start.getRowIndex() + "," + start.getCellIdx() + ") End: (" + end.getRowIndex() + "," + end.getCellIdx() + ")";
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
        return this.start.getRowIndex() == move.start.getRowIndex() &&
                this.start.getCellIdx() == move.start.getCellIdx() &&
                this.end.getRowIndex() == move.end.getRowIndex() &&
                this.end.getCellIdx() == move.end.getCellIdx();
    }

    /**
     * Checks if this move is the opposite of another move
     *
     * @param move - Other move to check with
     * @return if the moves are opposite
     */
    public boolean reverseEquals(Move move) {
        return this.start.getRowIndex() == move.end.getRowIndex() &&
                this.start.getCellIdx() == move.end.getCellIdx() &&
                this.end.getRowIndex() == move.start.getRowIndex() &&
                this.end.getCellIdx() == move.start.getCellIdx();
    }
}
