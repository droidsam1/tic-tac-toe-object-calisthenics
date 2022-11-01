package com.droidsam.app;

public class Player {
    public static final Player X = new Player('X');
    public static final Player Y = new Player('Y');
    public static final Player NO_PLAYER = new Player('-');
    private final char mark;

    private Player(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }
}
