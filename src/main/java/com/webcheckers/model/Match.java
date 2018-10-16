package com.webcheckers.model;

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
