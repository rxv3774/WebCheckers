package com.webcheckers.model;

public class Position {
    private int row;
    private int cell;

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * Calculate difference between start and end position to see if it's a single move
     *
     * @param end: end position
     * @return true if single move
     */
    public boolean isSingleMove(Position end){
        return Math.abs(this.row - end.row) == 1 && Math.abs(this.cell - end.cell) == 1;
    }

    /**
     * Calculate difference between start and end position to see if it's a single move
     * @param end: end position
     * @return true if single move
     */
    public boolean isJumpMove(Position end){
        return Math.abs(this.row - end.row) == 2 && Math.abs(this.cell - end.cell) == 2;
    }

    /**
     * retrieve the position of the space that was jumped over in a jump move
     *
     * @param end: ending position
     * @return position of space jumped over
     */
    public Position getMiddle(Position end){
        if(this.row > end.row) {
            if(this.cell > end.cell) {
                return new Position(end.row+1, end.cell+1);
            }else {
                return new Position(end.row+1, this.cell+1);
            }
        } else {
            if(this.cell > end.cell) {
                return new Position(this.row+1, end.cell+1);
            } else {
                return new Position(this.row+1, this.cell+1);
            }
        }
    }

    /**
     * Check if piece is moving backwards
     *
     * @param end ending position of move
     * @param isRedPiece if the moved piece is red or not
     *
     * @return boolean if piece move is backward
     */
    public boolean moveBackwards(Position end, boolean isRedPiece){
        if(isRedPiece)
            return (this.row - end.row) > 0;
        return (end.row - this.row) > 0;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }
}
