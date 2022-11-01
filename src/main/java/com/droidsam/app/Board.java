package com.droidsam.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Coordinate, Player> squares;
    private final int size;


    public Board() {
        this.size = 3 * 3;
        this.squares = new HashMap<>(size);
    }

    public void place(Player player, Coordinate position) {
        squares.put(position, player);
    }

    public Player getPlayerAt(Coordinate position) {
        if (!squares.containsKey(position)) {
            return Player.NO_PLAYER;
        }
        return squares.get(position);
    }

    public Collection<Coordinate> getMainDiagonal() {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(i, i));
        }
        return coordinates;
    }

    public Collection<Coordinate> getInverseMainDiagonal() {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(i, 2 - i));
        }
        return coordinates;
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
            result &= player.equals(this.squares.get(Coordinate.of(columnIndex, i)));
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
            result &= player.equals(this.squares.get(Coordinate.of(i, rowIndex)));
        }
        return result;
    }

    @Deprecated
    public boolean hasPlayerACompleteMainDiagonal(Player player) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            result &= player.equals(this.squares.get(Coordinate.of(i, i)));
        }
        return result;
    }

    @Deprecated
    public boolean hasPlayerACompleteInverseDiagonal(Player player) {
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            result &= player.equals(this.squares.get(Coordinate.of(i, 2 - i)));
        }
        return result;
    }

    @Deprecated
    public boolean hasPlayerACompleteDiagonal(Player player) {
        if (hasPlayerACompleteMainDiagonal(player)) {
            return true;
        }
        return hasPlayerACompleteInverseDiagonal(player);
    }

    public boolean isFull() {
        return squares.size() == size;
    }
}
