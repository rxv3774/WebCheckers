package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Match;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.Random;

public class AI {

    /**
     * The enum Difficulty for the AI.
     */
    public enum DIFFICULTY {
        /**
         * Easy difficulty.
         */
        EASY,
        /**
         * Medium difficulty.
         */
        MEDIUM,
        /**
         * Hard difficulty.
         */
        HARD
    }

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

        DIFFICULTY diff;
        if(us.getName().contains("HARD")){
            diff = DIFFICULTY.HARD;
        } else if (us.getName().contains("MEDIUM")) {
            diff = DIFFICULTY.MEDIUM;
        } else {
            diff = DIFFICULTY.EASY;
        }

        ArrayList<Move> moves = match_copy.getBoard().getPossibleMoves(match_copy.getActiveColor());
        ArrayList<Move> moves_out = match.getBoard().getPossibleMoves(match.getActiveColor());

        if(diff == DIFFICULTY.EASY){
            return moves_out.get(random.nextInt(moves.size()));
        }

        long[] count = new long[moves.size()];
        long start_time = System.nanoTime();
        while(System.nanoTime() - start_time < 250 * 1000000) {
            Match _game = match_copy.deepCopy();
            ArrayList<Move> possibles = _game.getBoard().getPossibleMoves(_game.getActiveColor());
            int move_index = random.nextInt(possibles.size());
            count[move_index] += monteCarlo(_game, possibles.get(move_index), diff, 15);
        }

        long max = count[0];
        Move out = moves_out.get(0);

        for (int i = 0; i < count.length; i++) {
            if(count[i] > max){
                max = count[i];
                out = moves_out.get(i);
            }
        }

        return out;
    }

    /**
     * run a montecarlo simulation for the AI
     * @param match - match to run the simulation on
     * @param mv - initial move to make
     * @param diff - AI difficulty
     * @param moves - legal moves to make
     * @return number of pieces that white is ahead by after 15 moves
     */
    private static int monteCarlo(Match match, Move mv, DIFFICULTY diff, int moves) {
        match.addPendingMove(mv);
        match.doPendingMoves();
        match.changeActivePlayer();

        Board board = match.getBoard();

        ArrayList<Move> possibles = board.getPossibleMoves(match.getActiveColor());
        if(moves == 0 || !match.canPlay()){
            return board.getWhitePlayerPieceAdvantage();
        }
        else{
            Move mv2 = possibles.get(random.nextInt(possibles.size()));
            return monteCarlo(match, mv2, diff, moves-1);
        }
    }
}
