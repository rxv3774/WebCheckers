package com.webcheckers.model;

public class Spectator extends User {
    public Spectator(String name, Match match, ViewMode viewMode) {
        super(name, match, viewMode);
    }

    @Override
    public String toString() {
        return null;
    }
}
