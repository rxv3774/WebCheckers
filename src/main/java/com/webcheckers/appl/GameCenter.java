package com.webcheckers.appl;

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

    public List<Match> getMatches() {
        return matches;
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
        runningGames.add(game);
        game.start();
        return game;
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
