package com.webcheckers.model;

public class Player {

    /**
     * The players username.
     */
    private String name;
    private Match match;

    /**
     * Player constructor
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.match = null;
    }

    /**
     * Defines the player's match when a player joins a game
     *
     * @param match the match
     * @return true if the player was able to join the match.
     */
    public boolean playGame(Match match) {
        if (this.match == null) {
            this.match = match;
            return true;
        }
        return false;
    }

    /**
     * if the player is in a game return true.
     *
     * @return true if player is in game
     */
    public boolean isInGame() {
        return match != null;
    }

    /**
     * End game.
     */
    public void endGame() {
        if (match != null)
            this.match.end();
        this.match = null;
    }

    /**
     * Player objects with the same name
     * are considered equal.
     *
     * @param obj object ot compare to
     * @return true if Player's have the same name
     */
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) return false;
        Player that = (Player) obj;
        return this.name.equals(that.name);
    }

    /**
     * Get view mode string.
     *
     * @return the string
     */
    public String getViewMode() {
        if (match == null /*!match.getWhitePlayer().equals(this) && !match.getRedPlayer().equals(this)*/) {
//        if ( !match.getWhitePlayer().equals(this) && !match.getRedPlayer().equals(this) ) {
            return "SPECTATOR";
        } else {
            return "PLAY";
        }
    }

    /**
     * Gets the Player's username.
     *
     * @return Player username.
     */
    public String getName() {
        return this.name;
    }


    public Match getMatch() {
        return match;
    }

    public String toString() {
        return "{Player: " + name + "}";
    }
}