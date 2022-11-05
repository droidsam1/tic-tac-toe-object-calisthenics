package com.droidsam.app;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SquareMatrix {

    private final Map<Coordinate, Player> squares;
    private final int sideSize;

    public SquareMatrix(int sideSize) {
        this.sideSize = sideSize;
        squares = new HashMap<>(sideSize * sideSize);
    }

    public void put(Coordinate position, Player player) {
        squares.put(position, player);
    }

    public Player get(Coordinate position) {
        if (!squares.containsKey(position)) {
            return Player.NO_PLAYER;
        }
        return squares.get(position);
    }

    public TypeOfSquareRow getRowForPlayer(Player player) {
        if (hasPlayerThreeMarksIn(getCoordinatesOfMainDiagonal(), player)) {
            return TypeOfSquareRow.DIAGONALLY;
        }
        if (hasPlayerThreeMarksIn(getCoordinatesOfInverseMainDiagonal(), player)) {
            return TypeOfSquareRow.DIAGONALLY;
        }

        return TypeOfSquareRow.NONE;

    }

    private Collection<Coordinate> getCoordinatesOfMainDiagonal() {
        return IntStream.range(0, sideSize).mapToObj(i -> Coordinate.of(i, i)).collect(Collectors.toList());
    }

    private Collection<Coordinate> getCoordinatesOfInverseMainDiagonal() {
        return IntStream.range(0, sideSize).mapToObj(i -> Coordinate.of(i, sideSize - 1 - i)).collect(Collectors.toList());
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().filter(c -> this.get(c).equals(player)).count() == sideSize;
    }


}
