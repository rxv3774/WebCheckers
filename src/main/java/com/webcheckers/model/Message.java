package com.webcheckers.model;

public class Message {

    private String text;

    private enum Type {info, error}

    private Type type;

    public Message(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

}
