package com.droidsam.app.board;

import com.droidsam.app.player.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinatesMatrix {

    private final Map<Coordinate, Player> squares;
    private final int sideSize;

    public CoordinatesMatrix(int sideSize) {
        this.sideSize = sideSize;
        squares = new HashMap<>(sideSize * sideSize);
    }

    public void put(Coordinate position, Player player) {
        squares.put(position, player);
    }

    public MatrixStatus getFillingStatus() {
        if (this.squares.keySet().size() == sideSize * sideSize) {
            return MatrixStatus.FULL;
        }
        return MatrixStatus.NOT_FULL;
    }

    public Player get(Coordinate position) {
        if (!squares.containsKey(position)) {
            return Player.NO_PLAYER;
        }
        return squares.get(position);
    }

    public ThreeMarksInARowStatus getThreeMarksInARowFor(Player player) {
        if (hasPlayerThreeMarksInAnyDiagonal(player) || hasPlayerThreeMarksInAnyColumn(player) || hasPlayerThreeMarksInAnyRow(player)) {
            return ThreeMarksInARowStatus.HAS_THREE_MARKS_IN_A_ROW;
        }

        return ThreeMarksInARowStatus.NONE;
    }

    private Collection<Coordinate> getCoordinatesOfMainDiagonal() {
        return IntStream.range(0, sideSize).mapToObj(i -> Coordinate.of(i, i)).collect(Collectors.toList());
    }

    private Collection<Coordinate> getCoordinatesOfInverseMainDiagonal() {
        return IntStream.range(0, sideSize).mapToObj(i -> Coordinate.of(i, sideSize - 1 - i)).collect(Collectors.toList());
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().filter(c -> this.get(c).equals(player)).count() == 3;
    }

    private boolean hasPlayerThreeMarksInAnyDiagonal(Player player) {
        return hasPlayerThreeMarksIn(this.getCoordinatesOfMainDiagonal(), player) || hasPlayerThreeMarksIn(this.getCoordinatesOfInverseMainDiagonal(), player);
    }


    private boolean hasPlayerThreeMarksInAnyColumn(Player player) {
        return IntStream.range(0, sideSize).mapToObj(this::getCoordinatesOfColumn).anyMatch(coordinates -> hasPlayerThreeMarksIn(coordinates, player));
    }

    private boolean hasPlayerThreeMarksInAnyRow(Player player) {
        return IntStream.range(0, sideSize).mapToObj(this::getCoordinatesOfRow).anyMatch(coordinates -> hasPlayerThreeMarksIn(coordinates, player));
    }

    private Collection<Coordinate> getCoordinatesOfColumn(int columnNumber) {
        return IntStream.range(0, sideSize).mapToObj(i -> Coordinate.of(columnNumber, i)).collect(Collectors.toList());
    }

    private Collection<Coordinate> getCoordinatesOfRow(int rowNumber) {
        return IntStream.range(0, sideSize).mapToObj(i -> Coordinate.of(i, rowNumber)).collect(Collectors.toList());
    }

}
