package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.model.User;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Signs in and stores all users and their sessions.
 */
public class PlayerLobby {
    /**
     * Array of all the users in the current lobby.
     */
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Attempts to add a user to the array of users. Only works if new username is not already taken.
     *
     * @param user The new user that is being added.
     */
    public void addUser(User user) throws NullPointerException {
        Objects.requireNonNull(user);
        users.add(user);
    }

    public void removeUser(User user) throws NullPointerException {
        Objects.requireNonNull(user);
        users.remove(user);
    }

    public void signOut(User user){
        users.remove(user);
    }

    /**
     * Determines if the playerName fallows our naming convention.
     *
     * @param name the name that needs to be checked
     * @return true if it fallows our rules, false otherwise
     */
    public boolean isValidName(String name) {
        final String allowedChars = "abcdefghijklmnopqrtsuvwyxzABCDEFGHIJKLMNOPQRTSUVWYXZ0123456789 ";
        boolean allowed = true;

        if (!name.equals("")) {
            for (String c : name.split("")) {
                if (!allowedChars.contains(c)) allowed = false;
            }
        } else allowed = false;

        return allowed;
    }

    /**
     * Checks to see if the name name that is being created is already in use by another player.
     *
     * @param name The name of the player that is trying to be created.
     * @return Whether or not the user is taken.
     */
    public boolean usernameInUse(String name) {
        Player other = new Player(name);
        for (User user : users) {
            if (other.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getPlayersNamesAsArrayList() {
        ArrayList<String> names = new ArrayList<>();

        for (User user : users) {
            names.add(user.getName());
        }

        return names;
    }

    /**
     * Gets player object given name of player
     */
    public User getUserObject(String name) {
        for (User user : users) {
            if (user.getName().equals(name))
                return user;
        }
        return null;
    }

    /**
     * Gets the number of users in the lobby.
     *
     * @return Size of lobby.
     */
    public int getLobbySize() {
        return users.size();
    }

    public String getUserNamesAsString(String name) {
        ArrayList<String> usernameList = new ArrayList<>(getPlayersNamesAsArrayList());
        usernameList.remove(name);

        StringBuilder names = new StringBuilder();
        if (usernameList.size() > 0) {

            names.append("Other Players signed in: ");

            for (int x = 0; x < usernameList.size(); x++) {

                if (x < usernameList.size() - 1) {
                    names.append(usernameList.get(x)).append(", ");
                } else {
                    names.append(usernameList.get(x));
                }
            }
        } else {
            names.append("Number of users signed in: ").append(getLobbySize());
        }
        return names.toString();
    }
}
