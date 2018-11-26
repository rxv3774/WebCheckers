package com.webcheckers.model;

public class Message {

    private String text;

    public enum Type {info, error}

    public Type type;

    public static final String URL_HOME = "/";

    /**
     * The constant ERR_NO_OPPONENT.
     */
    public static final Message ERR_NO_OPPONENT = new Message("You have no opponent", Type.error);

    /**
     * The constant ERR_INVALID_MOVE.
     */
    public static final Message ERR_INVALID_MOVE = new Message("Invalid Move", Type.error);

    /**
     * The constant ERR_INVALID_MOVE.
     */
    public static final Message ERR_DOUBLE_MOVE = new Message("Move already made", Type.error);

    /**
     * The constant VALID_MOVE.
     */
    public static final Message VALID_MOVE = new Message("Valid Move", Type.info);

    /**
     * The constant ERR_NOT_SIGNED_IN.
     */
    public static final Message ERR_NOT_SIGNED_IN = new Message("Player not signed in", Type.error);

    /**
     * The constant MOVE_SUBMITTED.
     */
    public static final Message MOVE_SUBMITTED = new Message("Move submitted!", Type.info);

    /**
     * The constant ERR_NO_PENDING_MOVES
     */
    public static final Message ERR_NO_PENDING_MOVES = new Message("No pending Moves!", Type.error);

    /**
     * The constant ERR_DJ_AVAILABLE
     */
    public static final Message ERR_DJ_AVAILABLE = new Message("Double Jump is available! Must complete double jump before submitting.", Type.error);

    /**
     * The constant TRUE.
     */
    public static final Message TRUE = new Message("true", Type.info);

    /**
     * The constant FALSE.
     */
    public static final Message FALSE = new Message("false", Type.info);



    /**
     * The constant BACKUPMOVE
     */
    public static final Message BACKUPMOVE = new Message( "Backup Move requested", Type.info );


    /**
     * The constant WINNER.
     */
    public static final Message WINNER = new Message("<a href='" + URL_HOME + "'>You win!Click here to return home.</a>", Type.info);

    /**
     * The constant LOSER.
     */
    public static final Message LOSER = new Message("<a href='" + URL_HOME + "'>You lose... Click here to return home.</a>", Type.info);

    /**
     * The constant RESIGNED.
     */
    public static final Message RESIGNED = new Message("Your opponent has resigned", Type.info);


    /**
     * The constant PLAYER_RESIGNATION
     */
    public static final Message PLAYER_RESIGNATION = new Message( "You quit the game", Type.info );

    /**
     * The constant OPPONENT_RESIGN
     */
    public static final Message OPPONENT_RESIGN = new Message( "<a href='" + URL_HOME + "'>Your opponent quit the game.</a>", Type.error);


    public Message(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public Type getType() {
        return type;
    }
}
