package com.webcheckers.model;

public class Position {
    private int row;
    private int cell;

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * Retrieve the position of the space that was jumped over in a jump move
     *
     * @param end: ending position
     * @return position of space jumped over
     */
    public Position getMiddle(Position end) {
        if (this.row > end.row) {
            if (this.cell > end.cell) {
                return new Position(end.row + 1, end.cell + 1);
            } else {
                return new Position(end.row + 1, this.cell + 1);
            }
        } else {
            if (this.cell > end.cell) {
                return new Position(this.row + 1, end.cell + 1);
            } else {
                return new Position(this.row + 1, this.cell + 1);
            }
        }
    }

    public boolean isOutOfBounds() {
        return (row < 0 || row > 7 || cell < 0 || cell > 7);
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return String.format("ROW: %d; COL: %d", row, cell);
    }
}
