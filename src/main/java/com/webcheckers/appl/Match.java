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

    //private Move pendingMove;
    private boolean running;


    /*
     * Match object defined
     *
     * @param redPlayer: user who requested the match
     * @param whitePlayer: player who was selected to compete
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

    /*
     * get Player object for the red player
     *
     * @return Player: player object
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /*
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
