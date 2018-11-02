package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    public enum Type {SINGLE, KING}

    public enum Color {RED, WHITE}

    private Type type;
    private Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }


    /**
     * return the direction offset
     *
     * @return direction offset
     */
    private int direction() {
        return color == Color.RED ? 1 : -1;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
