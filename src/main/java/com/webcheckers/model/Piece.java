package com.webcheckers.model;

public class Piece {
    enum Type {SINGLE, KING}
    enum Color {RED, WHITE}

    private Type type;
    private Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
