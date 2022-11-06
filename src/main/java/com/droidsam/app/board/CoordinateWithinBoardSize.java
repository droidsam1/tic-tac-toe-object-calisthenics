package com.droidsam.app.board;

public class CoordinateWithinBoardSize extends Coordinate {

    private final int boardSize;

    private CoordinateWithinBoardSize(Coordinate coordinate, int boardSize) {
        super(coordinate);
        this.boardSize = boardSize;
        enforceLimits(this.x, this.y);
    }

    public static void enforceLimits(Coordinate coordinate, int boardSize) {
        new CoordinateWithinBoardSize(coordinate, boardSize);
    }

    private void enforceLimits(int x, int y) {
        if (x < 0 || x >= boardSize) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || y >= boardSize) {
            throw new IndexOutOfBoundsException();
        }
    }

}
