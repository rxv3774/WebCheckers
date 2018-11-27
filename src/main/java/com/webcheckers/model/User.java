package com.webcheckers.model;

public abstract class User {
    private String name;
    private Match match;
    private ViewMode viewMode;

    public enum ViewMode {
        SPECTATOR,
        PLAYER,
        NONE
    }

    public User(String name) {
        this.name = name;
        this.match = null;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    public ViewMode getViewMode() {
        if (match == null) {
            return ViewMode.NONE;
        }
        return viewMode;
    }

    public String getName() {
        return name;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public abstract String toString();
}
