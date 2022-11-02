package com.droidsam.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.droidsam.app.Player.NO_PLAYER;

public class Board {

    private final Map<Coordinate, Player> squares;
    private final int size;


    public Board() {
        this.size = 3;
        this.squares = new HashMap<>(getFullSize(size));
    }

    public void place(Player player, Coordinate position) {
        var status = getStatus();
        if (status != GameStatus.ONGOING) {
            return;
        }

        squares.put(position, player);
    }

    public GameStatus getStatus() {
        if (getWinner() != NO_PLAYER) {
            return GameStatus.WON;
        }
        if (isDraw()) {
            return GameStatus.DRAW;
        }
        return GameStatus.ONGOING;
    }

    public Player getPlayerAt(Coordinate position) {
        if (!squares.containsKey(position)) {
            return Player.NO_PLAYER;
        }
        return squares.get(position);
    }

    private Collection<Coordinate> getCoordinatesOfRow(int rowNumber) {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            coordinates.add(Coordinate.of(i, rowNumber));
        }
        return coordinates;
    }

    private Collection<Coordinate> getCoordinatesOfColumn(int columnNumber) {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            coordinates.add(Coordinate.of(columnNumber, i));
        }
        return coordinates;
    }

    private Collection<Coordinate> getCoordinatesOfMainDiagonal() {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            coordinates.add(Coordinate.of(i, i));
        }
        return coordinates;
    }

    private Collection<Coordinate> getCoordinatesOfInverseMainDiagonal() {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            coordinates.add(Coordinate.of(i, 2 - i));
        }
        return coordinates;
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().filter(c -> this.getPlayerAt(c).equals(player)).count() == size;
    }

    private boolean isFull() {
        return this.squares.values().stream().filter(p -> !p.equals(NO_PLAYER)).count() == getFullSize(size);
    }

    private int getFullSize(int size) {
        return size * size;
    }

    private boolean isDraw() {
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
        boolean result = false;
        for (int i = 0; i < size; i++) {
            result |= hasPlayerThreeMarksIn(this.getCoordinatesOfRow(i), player);
        }
        return result;
    }

    private boolean hasPlayerPlaceThreeMarksInColumn(Player player) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            result |= hasPlayerThreeMarksIn(this.getCoordinatesOfColumn(i), player);
        }
        return result;
    }

    private boolean hasPlayerPlaceThreeMarksInDiagonal(Player player) {
        return hasPlayerThreeMarksIn(this.getCoordinatesOfMainDiagonal(), player) || hasPlayerThreeMarksIn(this.getCoordinatesOfInverseMainDiagonal(), player);
    }

}
