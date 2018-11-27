package com.webcheckers.model;

/**
 * Spectator class, extends User
 */
public class Spectator extends User {
    /**
     * Spectator constructor
     *
     * @param name spectator's name
     */
    public Spectator(String name) {
        super(name, ViewMode.SPECTATOR);
    }

    /**
     * Returns the string translation of the spectator
     *
     * @return string translation of the spectator
     */
    @Override
    public String toString() {
        return String.format("{Spectator: %s}", getName());
    }

    /**
     * Spectator objects with the same name
     * are considered equal.
     *
     * @param object object to be compared to
     * @return true if the spectators have the same name
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        else if (object instanceof Spectator) {
            return this.getName().equals(((Spectator) object).getName());
        } else return false;
    }
}
