package com.webcheckers.model;

public class Message {

    private String text;

    public enum Type {info, error}

    public Type type;

    /**
     * The constant ERR_NO_OPPONENT.
     */
    public static final Message ERR_NO_OPPONENT = new Message("You have no opponent", Type.error);

    /**
     * The constant ERR_INVALID_MOVE.
     */
    public static final Message ERR_INVALID_MOVE = new Message("Invalid Move", Type.error);

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
     * The constant TRUE.
     */
    public static final Message TRUE = new Message("true", Type.info);

    /**
     * The constant FALSE.
     */
    public static final Message FALSE = new Message("false", Type.info);


    /**
     * The constant PLAYER_RESIGNATION
     */
    public static final Message PLAYER_RESIGNATION = new Message( "You quit the game", Type.info );

    /**
     * The constant OPPONENT_RESIGN
     */
    public static final Message OPPONENT_RESIGN = new Message( "Your opponent quit the game", Type.info);





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
