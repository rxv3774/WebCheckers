package com.webcheckers.appl;

import com.google.gson.Gson;
import com.webcheckers.model.Match;
import com.webcheckers.model.Player;

import java.util.ArrayList;

/*
 * Manage the match between players, using Json
 */
public class GameCenter {
    private Gson gson;
    private ArrayList<Match> matches = new ArrayList<>();

    public GameCenter(Gson gson) {
        gson = gson;
    }

    public Gson getGson() {
        return gson;
    }

    /*
     * Create a new match between two players
     *
     * @param redPlayer: user who requested the match
     * @param whitePlayer: player who was selected to compete
     */
    public void addMatch(Player redPlayer, Player whitePlayer) {
        matches.add(new Match(redPlayer, whitePlayer));
    }

    /*
     * If the player is already in a match
     *
     * @Param player: player object in question
     * @return boolean if the player is in a match already
     */
    public boolean containsPlayer(Player player) {
        for (Match match : matches) {
            if (match.getRedPlayer().equals(player) || match.getWhitePlayer().equals(player)) return true;
        }
        return false;
    }
}
