package com.droidsam.app;

import java.util.Objects;

public class Player {
    public static final Player X = new Player('X');
    public static final Player Y = new Player('Y');
    public static final Player NO_PLAYER = new Player('-');
    private final char mark;

    private Player(char mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return mark == player.mark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }
}
