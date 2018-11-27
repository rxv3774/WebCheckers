package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Match;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.util.ArrayList;
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
        Player us = match.getActivePlayer();

        ArrayList<Move> moves = match_copy.getBoard().getPossibleMoves(match_copy.getActiveColor());
        ArrayList<Move> moves_out = match.getBoard().getPossibleMoves(match.getActiveColor());

        return moves_out.get(random.nextInt(moves.size()));
    }
}
