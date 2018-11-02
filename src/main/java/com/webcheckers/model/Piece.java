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
     * check if the piece can make a move.
     *
     * @param rowOffset the row offset
     * @param colOffset the col offset
     * @return true if the piece can make a move
     */
    public boolean canMakeMove(int rowOffset, int colOffset) {
        int direction = direction();
        return (rowOffset == direction && Math.abs(colOffset) == 1) ||
                (rowOffset == direction * 2 && Math.abs(colOffset) == 2);
    }

    public void getPossibleMovesHelper(List<Move> moves, Space start, Board gameBoard, int endR, int endC) {
        Space end = gameBoard.getSpace(endR, endC);
        if (end != null) {
            Move move = new Move(start, end, gameBoard);
            if (move.isValid()) moves.add(move);
        }
    }

    /**
     * return moves the piece can make.
     *
     * @param start     the start
     * @param gameBoard the game board
     * @return list of moves that the piece can move
     */
    public List<Move> getPossibleMoves(Space start, Board gameBoard) {
        int direction = direction();

        List<Move> moves = new ArrayList<>();
        getPossibleMovesHelper(moves, start, gameBoard, start.getRowIndex() + direction, start.getCellIdx() + 1);
        getPossibleMovesHelper(moves, start, gameBoard, start.getRowIndex() + direction, start.getCellIdx() - 1);
        getPossibleMovesHelper(moves, start, gameBoard, start.getRowIndex() + 2 * direction, start.getCellIdx() + 2);
        getPossibleMovesHelper(moves, start, gameBoard, start.getRowIndex() + 2 * direction, start.getCellIdx() - 2);

        //Iterate through moves adding jumps to those that need it
        for (Move move : moves) {
            if (!move.isJump()) continue;
            Move move2 = move.MaxChain(gameBoard);
            while (move2 != null) {
                move2.connectToBoard(gameBoard);
                move.addMove(move2);
                move2 = move.MaxChain(gameBoard);
            }
        }

        return moves;
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
