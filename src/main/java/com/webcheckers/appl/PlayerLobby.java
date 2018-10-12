package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * Created by Brett Patterson on 10/11/2018.
 */
public class PlayerLobby {
    /**
     * Array of all the players in the current lobby.
     */
    private ArrayList<Player> players = new ArrayList<>();

    /**
     * Attempts to add a player to the array of players. Only works if new username is not already taken.
     * @param player The new player that is being added.
     */
    public void addPlayer(Player player) {
        if (isValidName(player.getUsername())) {
            players.add(player);
            System.out.println(player.getUsername() + " has been added"); // Print to website, not console

        }
        else {
            System.out.println("Username is already taken");
        }
    }

    /**
     * Checks to see if the username name that is being created is already in use by another player.
     * @param username The username of the player that is trying to be created.
     * @return Whether or not the user is taken.
     */
    public boolean isValidName(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints out the list of all the players in the lobby.
     * @return List of players.
     */
    public ArrayList<Player> printPlayers() { //TODO: fix so that it prints to the to the website, not the console.
        return players;
    }

    /**
     * Gets the number of players in the lobby.
     * @return Size of lobby.
     */
    public int getLobbySize() {
        return players.size();
    }
}
