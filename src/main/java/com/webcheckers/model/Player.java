package com.webcheckers.model;

/**
 * Player class, extends User. This class includes framework for tournament play, in the case we want to implement a third enhancement.
 */
public class Player extends User {
    private int gamesWon;
    private int gamesTied;
    private int gamesLost;

    private boolean isAI;

    /**
     * Player constructor
     *
     * @param name player's name
     */
    public Player(String name) {
        super(name, ViewMode.PLAYER);
        this.isAI = false;
    }

    public Player(String name, boolean isAI) {
        super(name, ViewMode.PLAYER);
        this.isAI = isAI;
    }

    public boolean isAI(){
        return isAI;
    }

    /**
     * Player objects with the same name
     * are considered equal.
     *
     * @param object object to be compared to
     * @return true if the players have the same name
     */
    public boolean equals(Object object) {
        if (object == this) return true;
        else if (object instanceof Player) {
            return this.getName().equals(((Player) object).getName());
        } else return false;
    }

    /**
     * Returns the string translation of the player
     *
     * @return string translation of the player
     */
    public String toString() {
        return String.format("{Player{Name: %s, Won: %d, Tied: %d, Lost: %d}",
                getName(),
                gamesWon,
                gamesTied,
                gamesLost
        );
    }

    /**
     * Get method for how many times the player has Won
     *
     * @return the number of times Won
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * This increments the number of times the player has won.
     */
    public void increaseGamesWon() {
        gamesWon++;
    }

    /**
     * Get method for how many times the player has drawed
     *
     * @return the number of times drawed
     */
    public int getGamesTied() {
        return gamesTied;
    }

    /**
     * This increments the number of times the player has drawed.
     */
    public void increaseGamesTied() {
        gamesTied++;
    }

    /**
     * Get method for how many times the player has lost
     *
     * @return the number of times lost
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * This increments the number of times the player has Lost.
     */
    public void increaseGamesLost() {
        gamesLost++;
    }
}