package com.droidsam.app;

import java.util.HashMap;
import java.util.Map;

public class Grid {

    private final Map<Coordinate, Player> squares;

    public Grid(int height, int wide) {
        this.squares = new HashMap<>(height * wide);
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
}
