package com.webcheckers.model;

public class Position {
    private int row;
    private int cell;

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /*
     * Calculate difference between start and end position to see if it's a single move
     *
     * @param end: end position
     * @return boolean if single move
     */
    public boolean isSingleMove(Position end){
        return Math.abs(this.row - end.row) <= 1 && Math.abs(this.cell - end.cell) <= 1;
    }

    /*
     * Check if piece is moving backwards
     *
     * @return boolean if piece move is backward
     */
    public boolean moveBackwards(Position end){
        return (this.row - end.row) > 0;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }
}
