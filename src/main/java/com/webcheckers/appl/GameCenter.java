package com.webcheckers.appl;

import com.webcheckers.model.Match;
import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * Manage the match between players, using Json
 */
public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    private List<Match> matches;
    private List<Match> unmatched;

    public GameCenter() {
        this.matches = new ArrayList<>();
        this.unmatched = new ArrayList<>();
    }

    /**
     * Creates a game and adds it to the games list. This calls the player start game.
     * methods as well.
     *
     * @param player1 - first player to add
     * @param player2 - second player to add
     * @return the created game
     */
    public Match createGame(Player player1, Player player2) {
        Match game = new Match();
        game.join(player1);
        game.join(player2);
        matches.add(game);
        game.start();
        return game;
    }


    /**
     * Running game.
     *
     * @param match the match
     * @return true if game is running
     */
    public boolean containsMatch(Match match) {
        return matches.contains(match);
    }

    /**
     * End the game.
     *
     * @param match the match
     */
    public void endGame(Match match) {
        match.close();
        matches.remove(match);
    }

    /**
     * If the player is already in a match
     *
     * @return boolean if the player is in a match already
     * @Param player: player object in question
     */
    public boolean containsPlayer(Player player) {
        for (Match match : matches) {
            if (match.matchContains(player))
                return true;
        }
        return false;
    }
}
