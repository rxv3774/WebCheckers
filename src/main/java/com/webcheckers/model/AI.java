package com.webcheckers.model;

import java.util.List;
import java.util.Random;

public class AI {

    private static Random random = new Random();

    /**
     * Gets ai move. selects random from provided possible, valid moves
     *  getPossibleMoves method will only send the jump moves if there are any available, prioritizing
     *  them about regular moves.
     *
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
