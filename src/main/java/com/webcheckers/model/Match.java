package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Match class, contains data for a game between two players and spectators
 */
public class Match {
    private Player redPlayer;
    private Player whitePlayer;
    private Player activePlayer;
    private Player winner;
    private Board board;

    private ArrayList<Spectator> spectators;

    private Move pendingMove, DJSecondPendingMove;
    private boolean running;

    /**
     * Match object defined
     */
    public Match() {
        this.board = new Board();
        this.running = false;

        spectators = new ArrayList<>();
    }

    /**
     * add a player to the match.
     *
     * @param player the player to add
     * @return true if the player could be added, false if not.
     */
    public boolean joinPlayer(Player player) {
        if (player != null) {
            if (player.joinGame(this)) {

                if (this.redPlayer == null) {
                    this.redPlayer = player;
                    board.initialize(Piece.Color.RED);
                    return true;
                } else if (this.whitePlayer == null) {
                    this.whitePlayer = player;
                    board.initialize(Piece.Color.WHITE);
                    return true;
                }
            }
        }
        return false;
    }

    public void joinSpectator(Spectator spectator) {
        if (spectator != null) {
            if (spectator.joinGame(this)) {
                spectators.add(spectator);
            }
        }
    }

    /**
     * Check if match contains the specified player
     *
     * @param player the player to check for
     * @return if the match contains the player
     */
    public boolean matchContains(Player player) {
        return this.redPlayer.equals(player) || this.whitePlayer.equals(player);
    }

    /**
     * Get active player player.
     *
     * @return the player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * get Player object for the red player
     *
     * @return Player: player object
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * get Player object for the white player
     *
     * @return Player: player object
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Get active color piece color.
     *
     * @return the piece color
     */
    public Piece.Color getActiveColor() {
        return (redPlayer == activePlayer) ? Piece.Color.RED : Piece.Color.WHITE;
    }

    /**
     * add a pending move to the match
     *
     * @param move: the move to add
     */
    public void addPendingMove(Move move) {
        if (pendingMove == null) this.pendingMove = move;
        else this.DJSecondPendingMove = move;
    }

    public void removePendingMove() {
        if (DJSecondPendingMove != null) DJSecondPendingMove = null;
        else pendingMove = null;
    }

    /**
     * return pending move
     * @return: pending move
     */
    public Move getPendingMove() {
        return pendingMove;
    }

    /**
     * Do pending moves.
     */
    public void doPendingMoves() {
        pendingMove.makeMove(this.board);
        this.pendingMove = null;
        if (DJSecondPendingMove != null) {
            DJSecondPendingMove.makeMove(this.board);
            this.DJSecondPendingMove = null;
        }
    }

    /**
     * check if there are any pending moves
     *
     * @return true if there is a pending move
     */
    public boolean hasPendingMoves() {
        return this.pendingMove != null;
    }

    /**
     * check if there are any pending Double Jump second moves
     *
     * @return true if there is a pending move
     */
    public boolean hasPendingDJMoves() {
        return this.DJSecondPendingMove != null;
    }

    /**
     * if there active player can make a move or not
     *
     * @return true if the active player has possible moves to make
     */
    public boolean canPlay() {
        return board.hasPossibleMoves(getActiveColor());
    }

    /**
     * checks if a double jump is available
     *
     * @return true is there is an available second jump
     */
    public boolean doubleJumpAvailable() {
        if (pendingMove.isJumpMove() && DJSecondPendingMove == null) {
            Position end = pendingMove.getEnd();
            Space space = board.getSpace(end);
            return space.hasSecondJumpAvailable(getActiveColor(), board, pendingMove);
        } else
            return false;
    }

    public Move chooseAISecondJump() {
        Position end = pendingMove.getEnd();
        Space space = board.getSpace(end);
        return space.AIChooseSecondJump(getActiveColor(), board, pendingMove);
    }

    public List<Move> getPossibleMoves() {
        return board.getPossibleMoves(getActiveColor());
    }

    /**
     * end game and set the winner to the player whose turn it is not.
     */
    public void declareWinner() {
        end();
        if (activePlayer == redPlayer) winner = whitePlayer;
        if (activePlayer == whitePlayer) winner = redPlayer;
    }

    public boolean isWinner(Player player) {
        return winner == player;
    }

    /**
     * Gets opponent.
     *
     * @param player the player
     * @return the players opponent
     */
    public Player getOpponent(Player player) {
        return player.equals(redPlayer) ? whitePlayer : redPlayer;
    }

    /**
     * Gets game board.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Checks if both players have joined
     *
     * @return true if both players have joined.
     */
    public boolean ready() {
        return redPlayer != null && whitePlayer != null;
    }

    /**
     * Start match.
     *
     * @return true if the game was started otherwise false.
     */
    public boolean start() {
        if (!ready()) return false;
        running = true;
        activePlayer = redPlayer;
        return true;
    }

    /**
     * End the match.
     */
    public void end() {
        running = false;
    }

    /**
     * Delete the match.
     */
    public void close() {
        if (redPlayer != null) redPlayer.endGame();
        if (whitePlayer != null) whitePlayer.endGame();
        redPlayer = null;
        whitePlayer = null;
        spectators.clear();
    }

    /**
     * Is running.
     *
     * @return true if game is running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Check if match has a winner.
     *
     * @return true if game has winner
     */
    public boolean hasWinner() {
        return winner != null;
    }

    /**
     * Changes the current player. if it's red, makes it white and vise versa
     */
    public void changeActivePlayer() {
        activePlayer = activePlayer == redPlayer ? whitePlayer : redPlayer;
    }

    public boolean doPlayersMatch(Player p1, Player p2) {
        return p1.equals(p2);
    }

    /**
     * Deep copy game.
     * @return deep copy of the game.
     */
    public Match deepCopy() {
        Match cp = new Match();
        cp.board = this.board.deepCopy();
        cp.activePlayer = this.activePlayer;
        cp.redPlayer = redPlayer;
        cp.whitePlayer = whitePlayer;
        return cp;
    }

}
