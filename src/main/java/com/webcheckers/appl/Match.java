package com.webcheckers.appl;

import com.webcheckers.model.*;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */
public class Match {
    private Player redPlayer;
    private Player whitePlayer;
    private Player activePlayer;
    private Player winner;
    private Board board;

    private Move pendingMove;
    private boolean running;


    /**
     * Match object defined
     */
    public Match() {
        this.board = new Board();
        this.running = false;
    }

    /**
     * add a player to the match.
     * @param player the player to add
     * @return true if the player could be added, false if not.
     */
    public boolean join(Player player){
        if(player.playGame(this)) {
            if (this.redPlayer == null) {
                this.redPlayer = player;
                board.initialize(Piece.Color.RED);
                return true;
            }
            if (this.whitePlayer == null) {
                this.whitePlayer = player;
                board.initialize(Piece.Color.WHITE);
                return true;
            }
        }
        return false;
    }

    /**
     * Get active player player.
     * @return the player
     */
    public Player getActivePlayer(){
        return activePlayer;
    }

    /**
     * get Player object for the red player
     *
     * @return Player: player object
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * get Player object for the white player
     *
     * @return Player: player object
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Get active color piece color.
     * @return the piece color
     */
    public Piece.Color getActiveColor(){
        if(redPlayer == activePlayer) {
            return Piece.Color.RED;
        } else {
            return Piece.Color.WHITE;
        }
    }

    /**
     * End turn.
     * Swaps active player.
     */
    public void endTurn(){
        if(activePlayer == redPlayer) {
            activePlayer = whitePlayer;
        }else{
            activePlayer = redPlayer;
        }
    }

    /**
     * Gets opponent.
     * @param player the player
     * @return the players opponent
     */
    public Player getOpponent(Player player) {
        return player.equals(redPlayer) ? whitePlayer : redPlayer;
    }

    /**
     * Gets game board.
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Checks for both players joining.
     * @return true if both players have joined.
     */
    public boolean ready(){
        return redPlayer != null && whitePlayer != null;
    }

    /**
     * Is jump availible boolean.
     * @return true if jump availible
     */
    public boolean isJumpAvailible(){
        return board.isJumpAvailible(getActiveColor());
    }

    /**
     * Add a pending move.
     * @param move the move
     */
    public void addPendingMove(Move move){
        if(this.pendingMove == null){
            this.pendingMove = move;
        }else {
            this.pendingMove.addMove(move);
        }
    }

    /**
     * Check if game has pending move.
     * @return true if game has a move
     */
    public boolean hasPendingMove(){
        return this.pendingMove != null;
    }

    /**
     * Validate the move.
     * @param move the move
     * @return true if move is valid
     */
    public boolean validateMoveOnChain(Move move){
        if(this.pendingMove == null) return false;
        return move.isValid(pendingMove);
    }

    /**
     * Start match.
     * @return true if the game was started otherwise false.
     */
    public boolean start(){
        if(!ready()) return false;
        running = true;
        activePlayer = redPlayer;
        return true;
    }

    /**
     * End the match.
     */
    public void end(){
        running = false;
    }

    /**
     * Delete the match.
     */
    public void close(){
        if(redPlayer != null) redPlayer.endGame();
        if(whitePlayer != null) whitePlayer.endGame();
        redPlayer=null;
        whitePlayer=null;
    }

    /**
     * Is runnning.
     * @return true if game is running
     */
    public boolean isRunning(){
        return running;
    }
}
