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

    public User(String name, Match match, ViewMode viewMode) {
        this.name = name;
        this.match = match;
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

    public Match getMatch() {
        return match;
    }

    public abstract String toString();
}
