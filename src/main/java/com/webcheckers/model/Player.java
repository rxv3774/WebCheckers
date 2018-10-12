package com.webcheckers.model;

public class Player {

    /**
     * The players username.
     */
    private String username;

    /**
     * Player constructor
     * @param username
     */
    public Player(String username) {
        this.username = username;
    }

    /**
     * Gets the Player's username.
     * @return Player username.
     */
    public String getUsername() {
        return this.username;
    }
}