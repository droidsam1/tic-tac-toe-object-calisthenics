package com.droidsam.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.droidsam.app.Player.NO_PLAYER;

public class Board {

    private final Map<Coordinate, Player> squares;


    public Board() {
        this.squares = new HashMap<>(3 * 3);
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

    private Collection<Coordinate> getRow(int rowNumber) {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(i, rowNumber));
        }
        return coordinates;
    }

    private Collection<Coordinate> getColumn(int columnNumber) {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(columnNumber, i));
        }
        return coordinates;
    }

    private Collection<Coordinate> getMainDiagonal() {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(i, i));
        }
        return coordinates;
    }

    private Collection<Coordinate> getInverseMainDiagonal() {
        Collection<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coordinates.add(Coordinate.of(i, 2 - i));
        }
        return coordinates;
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().filter(c -> this.getPlayerAt(c).equals(player)).count() == 3;
    }

    private boolean isFull() {
        return this.squares.values().stream().filter(p -> !p.equals(NO_PLAYER)).count() == 9;
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
        return hasPlayerThreeMarksIn(this.getRow(0), player) || hasPlayerThreeMarksIn(this.getRow(1), player) || hasPlayerThreeMarksIn(this.getRow(2), player);
    }

    private boolean hasPlayerPlaceThreeMarksInColumn(Player player) {
        return hasPlayerThreeMarksIn(this.getColumn(0), player) || hasPlayerThreeMarksIn(this.getColumn(1), player) || hasPlayerThreeMarksIn(this.getColumn(2), player);
    }

    private boolean hasPlayerPlaceThreeMarksInDiagonal(Player player) {
        return hasPlayerThreeMarksIn(this.getMainDiagonal(), player) || hasPlayerThreeMarksIn(this.getInverseMainDiagonal(), player);
    }

}
