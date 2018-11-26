package com.webcheckers.model;

public class Piece {
    public enum Type {SINGLE, KING} //Type of Piece

    public enum Color {RED, WHITE} //Color of Piece

    private Type type;
    private Color color;

    /*
     * New Piece
     *
     * @param type - the type of piece
     * @param color - color of the piece
     *
     * @return Piece - the piece object
     */
    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * make the piece a king
     */
    public void makeKing() {
        this.type = Type.KING;
    }

    /**
     * check if the piece is a king
     *
     * @return true if the piece is a king
     */
    public boolean isKing() {
        return this.type == Type.KING;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
