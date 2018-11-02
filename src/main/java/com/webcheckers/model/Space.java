package com.webcheckers.model;

import java.util.List;

public class Space {
    private int cellIndex, rowIndex;
    private Piece piece;

    public Space(int cellIndex, int rowIndex) {
        this.cellIndex = cellIndex;
        this.rowIndex = rowIndex;
        this.piece = null;
    }

    public Space(int cellIndex, int rowIndex, Piece piece) {
        this.cellIndex = cellIndex;
        this.rowIndex = rowIndex;
        this.piece = piece;
    }

    /**
     * Initializes the space if it is valid to have a starting piece on it
     */
    public void initialize() {
        Piece piece = null;
        if (isValid()) {
            if (rowIndex < 3) {
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
            }
            if (rowIndex > 4) {
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
            }
        }
        this.piece = piece;
    }


    public int getCellIdx() {
        return cellIndex;
    }

    public int getRowIndex() {
        return rowIndex;
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
    public Piece.Color pieceColor() {
        if (piece == null) return null;
        return piece.getColor();
    }

    /**
     * Checks to see if the space is black and/or already has a piece on it
     * If it is an open, black space it returns true
     * otherwise returns false
     *
     * @return true if the space can have a piece on it.
     */
    public boolean isValid() {
        if (rowIndex % 2 == 0) {
            if (cellIndex % 2 == 1 && piece == null)
                return true;
            else
                return false;
        } else if (rowIndex % 2 == 1) {
            if (cellIndex % 2 == 0 && piece == null)
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * Deep copy space.
     *
     * @return deep copy of the space
     */
    public Space deepCopy() {
        Space cp = new Space(this.cellIndex, this.rowIndex);
        cp.setPiece(this.piece);
        return cp;
    }

    /**
     * remove the piece in this space
     *
     * @return the piece that previsouly occupied the space
     */
    public Piece removePiece() {
        Piece tmp = this.piece;
        this.piece = null;
        return tmp;
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

    /**
     * Testing to set piece.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
