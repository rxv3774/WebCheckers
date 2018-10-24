package com.webcheckers.model;

public class Player {

    /**
     * The players username.
     */
    private String name;

    /**
     * Player constructor
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the Player's username.
     * @return Player username.
     */
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "{Player: " + name + "}";
    }
}