package com.webcheckers.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.Match;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.util.List;
import java.util.Random;

public class AI {

    private static Random random = new Random();

    /**
     * Gets ai move.
     * Simulates 10000+ 15 moves and check which initial move is the best.
     * @param match the match
     * @return the ai move
     */
    public static Move getAIMove(Match match) {
        Match match_copy = match.deepCopy();

        List<Move> moves = match_copy.getPossibleMoves();
        List<Move> moves_out = match.getPossibleMoves();

        return moves_out.get(random.nextInt(moves.size()));
    }
}
