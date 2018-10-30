package com.webcheckers.model;

import java.util.List;

import com.webcheckers.appl.Match;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Move.
 *
 * @author toothlessG help with GSON, store the move in this class
 */
public class Move {

    private Space start;
    private Space end;
    private Space jumpSpace;

    private Move nextMove;

    /**
     * Instantiates a new Move.
     * @param start the start space
     * @param end   the end space
     */
    public Move(Space start, Space end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Move.
     * @param start the start space
     * @param end   the end space
     * @param board the board to make move on
     */
    public Move(Space start, Space end, Board board){
        this.start = start;
        this.end = end;
        findMiddle(board);
    }

    /**
     * Connect to board
     * @param board the board
     */
    public void connectToBoard(Board board){
        this.start = board.getSpace(start.getRowIndex(), start.getCellIdx());
        this.end = board.getSpace(end.getRowIndex(), end.getCellIdx());
        findMiddle(board);
    }

    /**
     * find the middle space of the move
     * @param board board to take space from
     */
    private void findMiddle(Board board){
        if(Math.abs(start.getRowIndex() - end.getRowIndex()) == 2 && Math.abs(start.getCellIdx() - end.getCellIdx()) == 2){
            int colOff = start.getCellIdx() + (end.getCellIdx() - start.getCellIdx()) / 2;
            int rowOff = start.getRowIndex() + (end.getRowIndex() - start.getRowIndex()) / 2;
            this.jumpSpace = board.getSpace(rowOff, colOff);
        }
    }

    /**
     * Add move.
     * @param move the move
     */
    public void addMove(Move move){
        if(this.nextMove == null){
            this.nextMove = move;
        }else {
            nextMove.addMove(move);
        }
    }

    /**
     * Has next move boolean.
     * @return true if move has next move
     */
    public boolean hasNextMove(){
        return nextMove != null;
    }

    /**
     * Remove last move.
     */
    public void removeLastMove(){
        if(nextMove == null) return;
        if(nextMove.nextMove == null){
            nextMove = null;
        }else{
            nextMove.removeLastMove();
        }
    }

    /**
     * Make move.
     */
    public void makeMove(){
        start.movePieceTo(end);
        if(isJump()) jumpSpace.removePiece();
        if(nextMove != null){
            nextMove.makeMove();
        }
    }

    /**
     * Checks if this move is valid given no other moves before it
     * @return true if move is valid
     */
    public boolean isValid(){
        return validateWithStart(start, null);
    }

    /**
     * Validates a move based on a chain of other moves. AKA is this move able to be made as
     * part of that move chain.
     * @param move the move
     * @return true if move is valid
     */
    public boolean isValid(Move move){
        //This start should not have a piece as it is part of a chain, we simulate a piece instead
        //For multiple moves, both must be a jump
        if(!this.isJump() || !move.isJump()) return false;
        //Simulate the start place of this move with a the piece that is at the start of the chain
        //This is to verify that the current move being made is legal
        Space simStart = new Space(start.getCellIdx(), start.getRowIndex(), move.start.getPiece());
        Move last = move;
        while(last.nextMove != null){
            //Ensure that this move is not a repeat or a backwards move.
            if(this.equals(last) || this.reverseEquals(last)) return false;
            last = last.nextMove;
        }
        //Check again to capture last case
        if(this.equals(last) || this.reverseEquals(last)) return false;
        return validateWithStart(simStart, last.start);
    }

    /**
     * validate the move
     * @param start start space
     * @param prev The previous space so that we don't jump backwards.
     * @return true if the move is valid
     */
    private boolean validateWithStart(Space start, Space prev){
        if(start.hasPiece()){
            if(this.end == prev) return false;
            if(isJump()) {
                return start.canMoveTo(end) && jumpSpace.hasPiece() && jumpSpace.pieceColor() != start.pieceColor();
            }
            return start.canMoveTo(end);
        }
        return false;
    }

    /**
     * Is jump boolean.
     * @return true if move is a jump
     */
    public boolean isJump(){
        return jumpSpace != null;
    }

    /**
     * Check that all jumps have been taken.
     * @param board the board to check on
     * @return true if all jumps have been taken.
     */
    public boolean isMaxChain(Board board) {
        return this.MaxChain(board) == null;
    }

    /**
     * Get the move that needs to be completed to get to the max jumps.
     * @param board the board
     * @return the move
     */
    public Move MaxChain(Board board){
        return MaxChainRec(board.deepCopy(), start.getPiece());
    }

    /**
     * Recurse to check that the maxchain is taken
     * This is no longer recursive but the name was kept for compatibility
     * @param board board to check on
     * @param piece piece to check maxchain for
     * @return null if max chain is complete or the move that needs to be made if not
     */
    private Move MaxChainRec(Board board, Piece piece){
        Move last = this;
        while(last.nextMove != null){
            last = last.nextMove;
        }
        Space simStart = new Space(last.end.getCellIdx(), last.end.getRowIndex(), piece);
        List<Move> moves = piece.getPossibleMoves(simStart, board);
        for(Move move : moves){
            if(move.end.getCellIdx() == this.start.getCellIdx() && move.end.getRowIndex() == this.start.getRowIndex()){
                continue;
            }
            //If the move is valid on this chain
            if(move.isValid(this)){
                return move;
            }
        }
        return null;
    }

    /**
     * string representation of the move
     * @return string rep
     */
    public String toString() {
        return "Start: (" + start.getRowIndex() + "," + start.getCellIdx() + ") End: (" + end.getRowIndex() + "," + end.getCellIdx() +")";
        //return null;
    }

    /**
     * Checks equality by positioning of the start and end spaces of the move
     * @param other - Other move to check with
     * @return If the moves are equal
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Move)) return false;
        Move move = (Move) other;
        return this.start.getRowIndex() == move.start.getRowIndex() &&
                this.start.getCellIdx() == move.start.getCellIdx() &&
                this.end.getRowIndex() == move.end.getRowIndex() &&
                this.end.getCellIdx() == move.end.getCellIdx();
    }

    /**
     * Checks if this move is the opposite of another move
     * @param move - Other move to check with
     * @return if the moves are opposite
     */
    public boolean reverseEquals(Move move){
        return this.start.getRowIndex() == move.end.getRowIndex() &&
                this.start.getCellIdx() == move.end.getCellIdx() &&
                this.end.getRowIndex() == move.start.getRowIndex() &&
                this.end.getCellIdx() == move.start.getCellIdx();
    }
}
