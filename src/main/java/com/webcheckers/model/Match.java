package com.webcheckers.model;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */
public class Match {
    private Player redPlayer;
    private Player whitePlayer;

    /*
     * Match object defined
     *
     * @param redPlayer: user who requested the match
     * @param whitePlayer: player who was selected to compete
     */
    public Match(Player redPlayer, Player whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
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
}
