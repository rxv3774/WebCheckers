package com.webcheckers.model;

/**
 * Abstract User class
 */
public abstract class User {
    private String name;
    private Match match;
    private ViewMode viewMode;

    public enum ViewMode {
        SPECTATOR,
        PLAYER
    }

    /**
     * User constructor
     *
     * @param name user's name
     * @param viewMode user's view mode
     */
    public User(String name, ViewMode viewMode) {
        this.name = name;
        this.match = null;
        this.viewMode = viewMode;
    }

    /**
     * Defines the user's match when a user joins a game
     *
     * @param match the match
     * @return true if the user was able to joinPlayer the match.
     */
    public boolean joinGame(Match match) {
        if (this.match == null) {
            this.match = match;
            return true;
        } else return false;
    }

    /**
     * Returns whether or not the user is in a game
     *
     * @return true if the user is in a game
     */
    public boolean isInGame() {
        return match != null;
    }

    /**
     * Ends the game.
     */
    public void endGame() {
        if (match != null) {
            this.match.end();
            this.match = null;
        }
    }

    /**
     * Get view mode type.
     *
     * @return the view mode
     */
    public String getViewMode() {
        switch (viewMode) {
            case PLAYER:
                return "PLAY";
            case SPECTATOR:
                return "SPECTATOR";
            default:
                return null;
        }
    }

    /**
     * Gets the user's username.
     *
     * @return user's username.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's match
     *
     * @return user's match
     */
    public Match getMatch() {
        return match;
    }

    public abstract String toString();

    public abstract boolean equals(Object object);
}
