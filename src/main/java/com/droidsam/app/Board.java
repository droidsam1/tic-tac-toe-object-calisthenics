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

    public Collection<Coordinate> getRow(int rowNumber) {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(i, rowNumber));
        }
        return coordinates;
    }

    public Collection<Coordinate> getColumn(int columnNumber) {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(columnNumber, i));
        }
        return coordinates;
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

    public boolean isFull() {
        return squares.size() == size;
    }
}
