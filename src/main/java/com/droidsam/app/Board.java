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
        int columnIndex = 0;
        boolean result = false;
        while (columnIndex < 3 && !result) {
            result = hasCompleteRow(player, columnIndex);
            columnIndex++;
        }
        return result;
    }

    private boolean hasCompleteRow(Player player, int columnIndex) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            result &= player.equals(this.grid.get(Coordinate.of(columnIndex, i)));
        }
        return result;
    }

    public boolean hasPlayerACompleteColumn(Player player) {
        int rowIndex = 0;
        boolean result = false;
        while (rowIndex < 3 && !result) {
            result = hasCompleteColumn(player, rowIndex);
            rowIndex++;
        }
        return result;
    }

    private boolean hasCompleteColumn(Player player, int rowIndex) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            result &= player.equals(this.grid.get(Coordinate.of(i, rowIndex)));
        }
        return result;
    }
}
