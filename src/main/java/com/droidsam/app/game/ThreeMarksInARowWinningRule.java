package com.droidsam.app.game;

import com.droidsam.app.board.Coordinate;
import com.droidsam.app.board.CoordinatesMatrix;
import com.droidsam.app.player.Player;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.droidsam.app.player.Player.NO_PLAYER;

public class ThreeMarksInARowWinningRule {

    private final int numberOfMarksToWin;
    private CoordinatesMatrix matrix;

    public ThreeMarksInARowWinningRule(int numberOfMarksToWin) {
        this.numberOfMarksToWin = numberOfMarksToWin;
    }

    public Player getWinner(CoordinatesMatrix matrix) {
        this.matrix = matrix;
        return Stream.of(Player.O, Player.X).filter(this::hasThreeMarksInARow).findFirst().orElse(NO_PLAYER);
    }

    private boolean hasThreeMarksInARow(Player player) {
        return hasPlayerThreeMarksInAnyDiagonal(player) || hasPlayerThreeMarksInAnyRow(player) || hasPlayerThreeMarksInAnyColumn(player);
    }

    private Collection<Coordinate> getCoordinatesOfMainDiagonal() {
        return IntStream.range(0, matrix.getSize()).mapToObj(i -> Coordinate.of(i, i)).collect(Collectors.toList());
    }

    private Collection<Coordinate> getCoordinatesOfInverseMainDiagonal() {
        return IntStream.range(0, matrix.getSize()).mapToObj(i -> Coordinate.of(i, matrix.getSize() - 1 - i)).collect(Collectors.toList());
    }

    private boolean hasPlayerThreeMarksInAnyDiagonal(Player player) {
        return hasPlayerThreeMarksIn(this.getCoordinatesOfMainDiagonal(), player) || hasPlayerThreeMarksIn(this.getCoordinatesOfInverseMainDiagonal(), player);
    }

    private boolean hasPlayerThreeMarksIn(Collection<Coordinate> positions, Player player) {
        return positions.stream().filter(c -> matrix.get(c).equals(player)).count() == numberOfMarksToWin;
    }

    private boolean hasPlayerThreeMarksInAnyColumn(Player player) {
        return IntStream.range(0, matrix.getSize()).mapToObj(this::getCoordinatesOfColumn).anyMatch(coordinates -> hasPlayerThreeMarksIn(coordinates, player));
    }

    private boolean hasPlayerThreeMarksInAnyRow(Player player) {
        return IntStream.range(0, matrix.getSize()).mapToObj(this::getCoordinatesOfRow).anyMatch(coordinates -> hasPlayerThreeMarksIn(coordinates, player));
    }

    private Collection<Coordinate> getCoordinatesOfColumn(int columnNumber) {
        return IntStream.range(0, matrix.getSize()).mapToObj(i -> Coordinate.of(columnNumber, i)).collect(Collectors.toList());
    }

    private Collection<Coordinate> getCoordinatesOfRow(int rowNumber) {
        return IntStream.range(0, matrix.getSize()).mapToObj(i -> Coordinate.of(i, rowNumber)).collect(Collectors.toList());
    }

}
