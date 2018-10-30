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
    public void initialize(){
        Piece piece = null;
        if(isValid()) {
            if (rowIndex < 3) {
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
            }
            if (rowIndex > 4) {
                piece = new Piece(Piece.Type.SINGLE, Piece.Color.WHITE);
            }
        }
        this.piece = piece;
    }


    public int getCellIdx() { return cellIndex; }

    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * check if the color matches the piece
     * @param color color to check if it matches
     * @return true if color matches false if not
     */
    public boolean pieceColorMatch(Piece.Color color){
        if(piece == null) return false;
        return piece.getColor() == color;
    }

    /**
     * get the color of piece
     * @return color of the piece
     */
    public Piece.Color pieceColor(){
        if(piece == null) return null;
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
        if(rowIndex%2 == 0) {
            if(cellIndex%2 == 1 && piece==null)
                return true;
            else
                return false;
        }
        else if(rowIndex%2 == 1){
            if(cellIndex%2 == 0 && piece==null)
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * Deep copy space.
     * @return deep copy of the space
     */
    public Space deepCopy(){
        Space cp = new Space(this.cellIndex, this.rowIndex);
        cp.setPiece(this.piece);
        return cp;
    }

    /**
     * remove the piece in this space
     * @return the piece that previsouly occupied the space
     */
    public Piece removePiece(){
        Piece tmp = this.piece;
        this.piece = null;
        return tmp;
    }

    /**
     * Checif the poiece in the space can move to the space space.
     * @param space the space
     * @return true if the piece can move false if not
     */
    public boolean canMoveTo(Space space){
        //If this piece is the same piece thats trying to move, that's a circular jump, its fine.
        if(space.hasPiece() && space.piece != this.piece) return false;
        return piece.canMakeMove(space.getRowIndex() - this.rowIndex, space.getCellIdx() - this.cellIndex);
    }

    /**
     * Move piece in the space to space space.
     * @param space the space
     */
    public void movePieceTo(Space space){
        space.piece = this.piece;
        this.piece = null;
    }

    /**
     * chekc if the piece in the space is jumpable
     * @param gameBoard the game board
     * @return true if piece is jumpable
     */
    public boolean hasJumpablePiece(Board gameBoard){
        if(piece == null) return false;
        List<Move> moves = piece.getPossibleMoves(this, gameBoard);
        for(Move move : moves){
            if(move.isJump()){
                return true;
            }
        }
        return false;
    }

    /**
     * check if the piece in the space
     * @param gameBoard the game board
     * @return the boolean
     */
    public boolean hasMoves(Board gameBoard){
        if(piece == null) return false;
        return piece.getPossibleMoves(this, gameBoard).size() > 0;
    }

    /**
     * Check if space has a piece
     * @return true if space has a piece false if not.
     */
    public boolean hasPiece(){
        return this.piece != null;
    }

    public Piece getPiece() { return this.piece; }

    /**
     * Testing to set piece.
     */
    public void setPiece(Piece piece){
        this.piece = piece;
    }
}
