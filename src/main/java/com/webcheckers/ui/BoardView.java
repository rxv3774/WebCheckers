package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.model.Row;

import java.util.Iterator;

public class BoardView {
    private Board board;

    public BoardView (){
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }
}
