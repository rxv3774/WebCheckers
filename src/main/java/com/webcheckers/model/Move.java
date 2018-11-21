package com.webcheckers.model;

/**
 * The type Move.
 *
 * @author toothlessG help with GSON, store the move in this class
 */
public class Move {

    private Position start;
    private Position end;

    /**
     * Instantiates a new Move.
     *
     * @param start the start space
     * @param end   the end space
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    /**
     * Make move.
     *
     * @param board: board object to get retrieve spaces from
     */
    public void makeMove(Board board){
        Space sStart = board.getSpace(start);
        Space sEnd = board.getSpace(end);
        sStart.movePieceTo(sEnd);
        if(start.isJumpMove(end)){
            Position middle = start.getMiddle(end);
            board.removePieceAtPosition(middle);
        }
    }

    /**
     * check is the move performed is valid
     *
     * @param board: board object to check spaces from
     * @param isRedPlayer: if the current player is red
     * @return true if the move is valid
     */
    public boolean isValid(Board board, boolean isRedPlayer){
        if(start.isSingleMove(end)){
            if(!board.spaceHasPiece(end)){
                return !start.moveBackwards(end, isRedPlayer);
            }else
                return false;
        }
        else if(start.isJumpMove(end)){
            if(!board.spaceHasPiece(end) && !start.moveBackwards(end, isRedPlayer)){
                Position middle = start.getMiddle(end);
                return board.spaceHasEnemyPiece(middle, isRedPlayer);
            }
            else
                return false;
        }
        else{
            System.out.println("failed");
            return false;
        }
    }

    /**
     * string representation of the move
     *
     * @return string rep
     */
    public String toString() {
        return "Start: (" + start.getRow() + "," + start.getCell() + ") End: (" + end.getRow() + "," + end.getCell() + ")";
        //return null;
    }

    /**
     * Checks equality by positioning of the start and end spaces of the move
     *
     * @param other - Other move to check with
     * @return If the moves are equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Move)) return false;
        Move move = (Move) other;
        return this.start.getRow() == move.start.getRow() &&
                this.start.getCell() == move.start.getCell() &&
                this.end.getRow() == move.end.getRow() &&
                this.end.getCell() == move.end.getCell();
    }
}
