package com.webcheckers.appl;

import spark.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
 * Signs in and stores all users and their sessions.
 */
public class PlayerLobby {
    /**
     * Array of all the players in the current lobby.
     */
    private ArrayList<Player> players = new ArrayList<>();
    private Map<String, Session> sessionMap = new HashMap<>();


    private static final String MESSAGE_ATTR = "message";
    private static final String MESSAGE_TYPE_ATTR = "messageType";
    private static final String VIEW_NAME = "signin.ftl";
    private static final String HOME_NAME = "home.ftl";


    /**
     * Attempts to add a player to the array of players. Only works if new username is not already taken.
     *
     * @param player The new player that is being added.
     */
    public void addPlayer(Player player) {
        players.add(player);
//            System.out.println( player.getName() + " has been added" ); // Print to website, not console
    }

    public void addPlayer(Player player, Session session) {
        players.add(player);
        sessionMap.put(player.getName(), session);
    }


    /**
     * Desc: This method determines if the playerName fallows our naming convention.
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
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Prints out the list of all the players in the lobby.
     *
     * @return List of players.
     */
    //TODO - Fix so that it prints to the to the website, not the console.
    public ArrayList<Player> printPlayers() {
        return players;
    }


    public ArrayList<String> getPlayersNames() {
        ArrayList<String> names = new ArrayList<>();

        for (Player player : players) {
            names.add(player.getName());
        }

        return names;
    }

    /*
     * return player object given name of player
     */
    public Player getPlayerObject(String name) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(name))
                return players.get(i);
        }
        return null;
    }


    /**
     * Desc: Gets the number of players in the lobby.
     *
     * @return Size of lobby.
     */
    public int getLobbySize() {
        return players.size();
    }

    /**
     * Desc: gets the players session
     *
     * @param playerName the players name to be used to get the session
     * @return returns the players session
     */
    public Session getPlayerSession(String playerName) {
        return sessionMap.get(playerName);
    }

    public String getPlayerNameLst(String name) {


        ArrayList<String> playerNameLst = new ArrayList<>(getPlayersNames());
        playerNameLst.remove(name);

        String names = "";
        if (playerNameLst.size() > 0) {

            names += "Other Players signed in: ";

            for (int x = 0; x < playerNameLst.size(); x++) {

                if (x < playerNameLst.size() - 1) {
                    names += playerNameLst.get(x) + ", ";
                } else {
                    names += playerNameLst.get(x);
                }
            }
        } else {
            names = "Number of players signed in: " + getLobbySize();
        }
        return names;
    }
}
