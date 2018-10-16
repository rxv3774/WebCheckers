package com.webcheckers.model;

public class Space extends Row {

    private int cellIndex;
    private Piece piece;

    public Space(int cellIndex, Piece piece) {
        this.cellIndex = cellIndex;
        this.piece = piece;
    }

    public int getCellIndex() { return cellIndex; }

    public boolean isValid() {
        return (cellIndex >= 0 && cellIndex <= 7);
    }

    public Piece getPiece() {
        this.piece;
    }
}