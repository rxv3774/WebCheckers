package com.webcheckers.model;

public class Space {
    private int cellIndex, rowIndex;
    private Piece piece;

    public Space(int cellIndex, int rowIndex) {
        this.cellIndex = cellIndex;

        this.rowIndex = rowIndex;
        if(rowIndex<3) {
            if(rowIndex%2 == 0) {
                if(cellIndex%2 == 1)
                    this.piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
                else
                    this.piece = null;
            }
            else if(rowIndex%2 == 1){
                if(cellIndex%2 == 0)
                    this.piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
                else
                    this.piece = null;
            }
        }
        else if (rowIndex>=5)
            if(rowIndex%2 == 0) {
                if(cellIndex%2 == 1)
                    this.piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
                else
                    this.piece = null;
            }
            else if(rowIndex%2 == 1){
                if(cellIndex%2 == 0)
                    this.piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
                else
                    this.piece = null;
            }
            else
                this.piece = null;
    }

    public int getCellIdx() { return cellIndex; }

    public boolean isValid() {
        if(rowIndex%2 == 0) {
            if(cellIndex%2 == 1)
                return true;
            else
                return false;
        }
        else if(rowIndex%2 == 1){
            if(cellIndex%2 == 0)
                return true;
            else
                return false;
        }
        return false;
    }

    public Piece getPiece() { return this.piece; }
}
