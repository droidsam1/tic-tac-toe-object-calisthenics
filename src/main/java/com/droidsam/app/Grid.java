package com.droidsam.app;

import java.util.HashMap;
import java.util.Map;

public class Grid {

    private final Map<Coordinate, Player> squares;
    private final int fullSize;

    public Grid(int height, int wide) {
        this.fullSize = height * wide;
        this.squares = new HashMap<>(fullSize);
    }

    public void put(Coordinate position, Player player) {
        squares.put(position, player);
    }

    public Player get(Coordinate position) {
        return this.squares.get(position);
    }

    public boolean isEmpty() {
        return squares.isEmpty();
    }

    public boolean isFull() {
        return this.squares.size() == fullSize;
    }
}
