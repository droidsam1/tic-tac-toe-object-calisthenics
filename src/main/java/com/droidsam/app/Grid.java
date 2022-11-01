package com.droidsam.app;

import java.util.Arrays;
import java.util.Objects;

public class Grid {

    private final Player[][] squares;

    public Grid(int height, int wide) {
        this.squares = new Player[height][wide];
    }

    public void put(Coordinate position, Player player) {
        squares[position.getX()][position.getY()] = player;
    }

    public Player get(Coordinate position) {
        return this.squares[position.getX()][position.getY()];
    }

    public boolean isEmpty() {
        return Arrays.stream(this.squares).flatMap(Arrays::stream).allMatch(Objects::isNull);
    }
}
