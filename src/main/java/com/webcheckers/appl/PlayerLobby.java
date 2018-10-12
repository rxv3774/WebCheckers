package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * Created by Brett Patterson on 10/11/2018.
 */
public class PlayerLobby {
    private ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }


}
