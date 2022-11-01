package com.droidsam.app;

public class Board {

    private final Grid grid;

    public Board() {
        this.grid = new Grid(3, 3);
    }

    public void place(Player player, Coordinate position) {
        grid.put(position, player);
    }

    public Player getPlayerAt(Coordinate position) {
        return grid.get(position);
    }

    public boolean isEmpty() {
        return this.grid.isEmpty();
    }

    public boolean hasPlayerACompleteRow(Player player) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            result &= player.equals(this.grid.get(Coordinate.of(0, i)));
        }
        return result;
    }
}
