package com.droidsam.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.droidsam.app.Player.NO_PLAYER;

public class Board {

    private final Map<Coordinate, Player> squares;
    private final int fullSize;


    public Board() {
        this.fullSize = 3 * 3;
        this.squares = new HashMap<>(fullSize);
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

    public Collection<Player> getPlayers() {
        return this.squares.values().stream().distinct().collect(Collectors.toList());
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().filter(c -> this.getPlayerAt(c).equals(player)).count() == 3;
    }

    private boolean isFull() {
        return this.squares.size() == this.fullSize;
    }

    public boolean isDraw() {
        return this.isFull() && NO_PLAYER.equals(this.getWinner());
    }

    public Player getWinner() {

        if (hasPlayerPlaceThreeMarksInColumn(Player.X) || hasPlayerPlaceThreeMarksInRow(Player.X) || hasPlayerPlaceThreeMarksInDiagonal(Player.X)) {
            return Player.X;
        }

        if (hasPlayerPlaceThreeMarksInColumn(Player.O) || hasPlayerPlaceThreeMarksInRow(Player.O) || hasPlayerPlaceThreeMarksInDiagonal(Player.O)) {
            return Player.O;
        }

        return NO_PLAYER;
    }

    private boolean hasPlayerPlaceThreeMarksInRow(Player player) {
        return hasPlayerThreeMarksIn(this.getRow(0), player) || hasPlayerThreeMarksIn(this.getRow(1), player) || hasPlayerThreeMarksIn(this.getRow(2), player);
    }

    private boolean hasPlayerPlaceThreeMarksInColumn(Player player) {
        return hasPlayerThreeMarksIn(this.getColumn(0), player) || hasPlayerThreeMarksIn(this.getColumn(1), player) || hasPlayerThreeMarksIn(this.getColumn(2), player);
    }

    private boolean hasPlayerPlaceThreeMarksInDiagonal(Player player) {
        return hasPlayerThreeMarksIn(this.getMainDiagonal(), player) || hasPlayerThreeMarksIn(this.getInverseMainDiagonal(), player);
    }

}
