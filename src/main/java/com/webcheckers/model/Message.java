package com.webcheckers.model;

public class Message {

    private String text;

    private enum Type {info, error};

    public Message(String text, Type type) {
        this.text = text;
        if (type == Type.error) {
            //
        }
        else {

        }
    }

    public String getText() {
        return this.text;
    }

//    public enum getType() {
//
//    }
}
