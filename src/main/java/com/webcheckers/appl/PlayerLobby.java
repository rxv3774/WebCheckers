package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Signs in and stores all users and their sessions.
 */
public class PlayerLobby {
    /**
     * Array of all the players in the current lobby.
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Attempts to add a player to the array of players. Only works if new username is not already taken.
     *
     * @param player The new player that is being added.
     */
    public void addPlayer(Player player) throws NullPointerException {
        Objects.requireNonNull(player);
        players.add(player);
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
    public boolean playerNameInUse(String name) {
        Player other = new Player(name);
        for (Player player : players) {
            if (other.equals(player) ) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getPlayersNamesAsArrayList() {
        ArrayList<String> names = new ArrayList<>();

        for (Player player : players) {
            names.add(player.getName());
        }

        return names;
    }

    /**
     * Gets player object given name of player
     */
    public Player getPlayerObject(String name) {
        for (Player player : players) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    /**
     * Gets the number of players in the lobby.
     *
     * @return Size of lobby.
     */
    public int getLobbySize() {
        return players.size();
    }

    public String getPlayerNamesAsString(String name) {
        ArrayList<String> playerNameLst = new ArrayList<>(getPlayersNamesAsArrayList());
        playerNameLst.remove(name);

        StringBuilder names = new StringBuilder();
        if (playerNameLst.size() > 0) {

            names.append("Other Players signed in: ");

            for (int x = 0; x < playerNameLst.size(); x++) {

                if (x < playerNameLst.size() - 1) {
                    names.append(playerNameLst.get(x)).append(", ");
                } else {
                    names.append(playerNameLst.get(x));
                }
            }
        } else {
            names.append("Number of players signed in: ").append(getLobbySize());
        }
        return names.toString();
    }
}
