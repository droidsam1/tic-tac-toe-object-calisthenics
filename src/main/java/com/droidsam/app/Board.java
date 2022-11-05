package com.droidsam.app;

public class Board {
    private final SquareMatrix squareMatrix;

    public Board() {
        this.squareMatrix = new SquareMatrix(3);
    }

    public void place(Player player, Coordinate position) {
        squareMatrix.put(position, player);
    }

    public Player getPlayerAt(Coordinate position) {
        return squareMatrix.get(position);
    }

    public TypeThreeMarksInARow getPlayerThreeMarksInARow(Player player) {
        return squareMatrix.getPlayerThreeMarksInARow(player);
    }

    public MatrixStatus getFillingStatus() {
        return squareMatrix.getFillingStatus();
    }
}
