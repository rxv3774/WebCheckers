package com.webcheckers.model;

/*
 * Object for the data of the match between two players
 *
 * Author: TeamD
 */
public class Match {
    private Player redPlayer;
    private Player whitePlayer;

    public Match(Player redPlayer, Player whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }
}
