package com.webcheckers.appl;

import com.google.gson.Gson;
import com.webcheckers.model.Match;
import com.webcheckers.model.Player;

import java.util.ArrayList;

public class GameCenter {
    private Gson gson;
    private ArrayList<Match> matches = new ArrayList<>();

    public GameCenter(Gson gson) {
        this.gson = gson;
    }

    public void addMatch(Player redPlayer, Player whitePlayer) {
        matches.add(new Match(redPlayer, whitePlayer));
    }

    public boolean containsPlayer(Player player) {
        for (Match match : matches) {
            if (match.getRedPlayer().equals(player) || match.getWhitePlayer().equals(player)) return true;
        }

        return false;
    }
}
